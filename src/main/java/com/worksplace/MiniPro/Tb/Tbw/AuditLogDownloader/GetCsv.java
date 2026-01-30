package com.worksplace.MiniPro.Tb.Tbw.AuditLogDownloader;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.worksplace.MiniPro.Tb.Tbw.AuditLogDownloader.Exception.UnauthorizedException;
import java.io.IOException;
import java.io.Writer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;

public class GetCsv {

    static ObjectMapper mapper = new ObjectMapper();
    static HttpClient http = HttpClient.newHttpClient();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss")
            .withResolverStyle(ResolverStyle.STRICT);
    static final String defaultUrl = "http://20.153.138.18:8080";
    static final String defaultUsername = "tenant@thingsboard.org";
    static final String defaultPassword = "tenant";
    static final String defaultStartTime = LocalDateTime.now()
            .minusDays(7)
            .format(formatter);
    static final String defaultEndTime = LocalDateTime.now()
            .format(formatter);
    static final String basePath = "/home/hingemakarand";
    static final Path path = createCsvPathForThisRun();


    public static void main(String[] args) throws IOException, InterruptedException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter the url (press enter for default url - %s) : ", defaultUrl);
        String url = normalize(sc.nextLine(), defaultUrl);
        System.out.printf("Enter the username (press enter for default username - %s) : ", defaultUsername);
        String username = normalize(sc.nextLine(), defaultUsername);
        System.out.printf("Enter the password (press enter for default password - %s) : ", defaultPassword);
        String password = normalize(sc.nextLine(), defaultPassword);
        String startTime;
        String endTime;
        while(true) {
            System.out.print("Enter the start time (press enter for default start time - past 7 days [dd-MM-yyyy HH:mm:ss]): ");
            startTime = normalize(sc.nextLine(), defaultStartTime);
            if(!isValidDateTime(startTime)) {
                System.out.println("Invalid start time. Try again. Format: dd-MM-yyyy HH:mm:ss");
                continue;
            }

            System.out.print("Enter the end time (press enter for default end time - today ongoing [dd-MM-yyyy HH:mm:ss]): ");
            endTime = normalize(sc.nextLine(), defaultEndTime);
            if(!isValidDateTime(endTime)) {
                System.out.println("Invalid end time. Try again. Format: dd-MM-yyyy HH:mm:ss");
                continue;
            }

            if(!isValidRange(startTime, endTime)) {
                System.out.println("Invalid date range. Start time must be before end time. Try again.");
                continue;
            }

            break;
        }
        System.out.print("Enter the action type (press enter for nothing) [login,logout (separated by commas)] : ");
        String actionTypeStr = sc.nextLine();

        String jwtToken = getToken(username,password,url);
        getAllLogs(jwtToken,url,parseReadableToEpoch(startTime),parseReadableToEpoch(endTime), actionTypeStr);
    }

    static String getToken(String username, String password, String url) throws IOException, InterruptedException {
        String json = String.format("""
                {
                "username": "%s",
                "password": "%s"
                }
                """, username, password);

        HttpRequest request = HttpRequest
                .newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url + "/api/auth/login"))
                .header("Content-Type","application/json")
                .build();
        HttpResponse<String> response;
        try {
            response = http
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            if(status == 401){
                throw new UnauthorizedException("Unauthorized (401)");
            }
            if (status >= 500) {
                throw new RuntimeException(
                        "Service is currently unavailable. Please try again later."
                );
            }
        }catch(ConnectException | ClosedChannelException e){
            throw new RuntimeException("Server unreachable");
        }catch (SocketTimeoutException e){
            throw new RuntimeException("The service is taking too long to respond.");
        }

        JsonNode jsonNode = mapper.readTree(response.body());
        return jsonNode.get("token").asText();

    }

    static void getAllLogs(String token, String baseUrl, long startTime, long endTime, String actionTypestr) throws IOException, InterruptedException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        boolean hasNext;
        int page = 0;
        do {
            StringBuilder url = new StringBuilder(
                    String.format(
                            "%s/api/audit/logs?pageSize=100&page=%d&startTime=%d&endTime=%d",
                            baseUrl, page, startTime, endTime
                    )
            );

            if(actionTypestr != null && !actionTypestr.isBlank()){
                url.append("&actionTypes=")
                        .append(isValidActionType(actionTypestr));
            }


            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .header("Authorization", "Bearer " + token)
                    .header("accept", "application/json")
                    .uri(URI.create(String.valueOf(url)))
                    .build();

            HttpResponse<String> response;
            try {
                response = http
                        .send(request, HttpResponse
                                .BodyHandlers
                                .ofString());
                int status = response.statusCode();
                if(status == 401){
                    throw new UnauthorizedException("Unauthorized (401)");
                }
                if (status >= 500) {
                    throw new RuntimeException(
                            "Service is currently unavailable. Please try again later."
                    );
                }
            }catch(ConnectException | ClosedChannelException e){
                throw new RuntimeException("Server unreachable");
            }catch (SocketTimeoutException e){
                throw new RuntimeException("The service is taking too long to respond.");
            }

            parseJsonToObject(response.body());
            JsonNode root = mapper.readTree(response.body());
            hasNext = root.get("hasNext").asBoolean();
            page++;
        }while(hasNext);
    }

    static String parseJsonToReadable(String raw) throws JsonProcessingException {
        JsonNode node = mapper
                .readTree(raw);

        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(node);
    }

    static void parseJsonToObject(String json) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<AuditCsvRow> itemList = new ArrayList<>();
        JsonNode root = mapper.readTree(json);
        JsonNode dataArray = root.get("data");
        for(JsonNode item : dataArray){
            AuditCsvRow obj = new AuditCsvRow();
            obj.setCreatedTime(parseEpochToReadable(item
                    .get("createdTime")
                    .asLong()));
            obj.setUserName(item
                    .get("userName")
                    .asText());
            obj.setEntityType(item
                    .get("entityId")
                    .get("entityType")
                    .asText());
            obj.setEntityName(item
                    .get("entityName")
                    .asText());
            obj.setActionType(item
                    .get("actionType")
                    .asText());
            obj.setActionStatus(item
                    .get("actionStatus")
                    .asText());
            itemList.add(obj);
        }
        writeCsv(itemList);
    }

    static void writeCsv(List<AuditCsvRow> list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Path csvPath = path;
        boolean writerHeader = Files.notExists(csvPath) || Files.size(csvPath) == 0;
        try (Writer writer = Files.newBufferedWriter(
                csvPath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            if (writerHeader) {
                writer.write("Timestamp,Entity Type,Entity Name,User,Type,Status\n");
            }

            ColumnPositionMappingStrategy<AuditCsvRow> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(AuditCsvRow.class);
            strategy.setColumnMapping(
                    "createdTime",
                    "entityType",
                    "entityName",
                    "userName",
                    "actionType",
                    "actionStatus"
            );
            StatefulBeanToCsv<AuditCsvRow> beanToCsv = new StatefulBeanToCsvBuilder<AuditCsvRow>(writer)
                    .withMappingStrategy(strategy)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(list);
        }
    }

    static String parseEpochToReadable(long time){
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return dateTime.format(formatter);
    }

    static long parseReadableToEpoch(String time){
        LocalDateTime dateTime = LocalDateTime.parse(time,formatter);
        return dateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    static String normalize(String input, String defaultValue){
        input = input.trim();
        return input.isEmpty() ? defaultValue : input;
    }

    static boolean isValidDateTime(String input){
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    static Path createCsvPathForThisRun() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        String timestamp = LocalDateTime.now().format(formatter);

        return Paths.get(basePath + "/audit-logs-" + timestamp + ".csv");
    }

    static String isValidActionType(String actionTypeStr){
        List<ActionType> validTypes = Arrays.stream(actionTypeStr.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(s -> {
                    try{
                        return ActionType.valueOf(s);
                    }catch (Exception e){
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        String raw = "";
        if(!validTypes.isEmpty()){
            raw = validTypes.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
        }
        return raw;
    }

    static boolean isValidRange(String start, String end){
        try {
            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
            return endTime.isAfter(startTime);

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
