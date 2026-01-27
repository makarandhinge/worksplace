package com.worksplace.MiniPro.Tb.Tbw.AuditLogDownloader;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetCsv {

    static ObjectMapper mapper = new ObjectMapper();
    static HttpClient http = HttpClient.newHttpClient();
    static final String defaultUrl = "http://20.153.138.18:8080";
    static final String defaultUsername = "tenant@thingsboard.org";
    static final String defaultPassword = "tenant@metOSX";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter the url (press enter for default url - %s) : ", defaultUrl);
        String url = sc.nextLine();
        System.out.printf("Enter the username (press enter for default username - %s) : ", defaultUsername);
        String username = sc.nextLine();
        System.out.printf("Enter the password (press enter for default password - %s) : ", defaultPassword);
        String password = sc.nextLine();

        if(url.isEmpty()){
            url = defaultUrl;
        }
        if(username.isEmpty()){
            username = defaultUsername;
        }
        if(password.isEmpty()){
            password = defaultPassword;
        }

        String jwtToken = getToken(username,password,url);
        String json = getAllLogs(jwtToken,url);
        String readableJson = parseJsonToReadable(json);
//        parseJsonToObject(readableJson);
        System.out.println(readableJson);

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

        HttpResponse<String> response = http
                .send(request,HttpResponse.BodyHandlers.ofString());
        String accessToken = response
                .body()
                .split(",")[0];
        return (String) accessToken
                .subSequence(10,accessToken.length() - 1);
    }

    static String getAllLogs(String token, String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("accept", "application/json")
                .uri(URI.create(url + "/api/audit/logs?pageSize=2&page=0"))
                .build();

        HttpResponse<String> response = http
                .send(request,HttpResponse
                        .BodyHandlers
                        .ofString());

        return response.body();
    }

    static String parseJsonToReadable(String raw) throws JsonProcessingException {
        JsonNode node = mapper
                .readTree(raw);

        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(node);
    }

    static void parseJsonToObject(String json) throws JsonProcessingException {
        List<AuditCsvRow> itemList = new ArrayList<>();
        JsonNode root = mapper.readTree(json);
        JsonNode dataArray = root.get("data");
        for(JsonNode item : dataArray){
            AuditCsvRow obj = new AuditCsvRow();
            obj.setCreatedTime(item
                    .get("actionData")
                    .get("entity")
                    .get("createdTime")
                    .asLong());
            obj.setUserName(item
                    .get("userName")
                    .asText());
            obj.setEntityType(item
                    .get("actionData")
                    .get("entity")
                    .get("id")
                    .get("entityType")
                    .asText());
            obj.setEntityName(item
                    .get("actionData")
                    .get("entity")
                    .get("name")
                    .asText());
            obj.setActionType(item
                    .get("actionType")
                    .asText());
            obj.setActionStatus(item
                    .get("actionStatus")
                    .asText());

            itemList.add(obj);
        }

        System.out.println(itemList);

    }
}
