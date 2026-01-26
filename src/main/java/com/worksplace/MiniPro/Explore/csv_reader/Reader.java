package com.worksplace.MiniPro.Explore.csv_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Reader {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new FileReader("/home/hingemakarand/Downloads/data.csv"));
		
		String line;
		List<AirQuality> list = new ArrayList<>();
		bf.readLine();
		while((line = bf.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line,",");
			AirQuality aqi = putInObject(token);
			list.add(aqi);
		}
		Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(o -> o.getAQI(),Collectors.counting()));
		for(Entry<Integer, Long> e : map.entrySet()) {
			System.out.println(e.getKey() + " -> " + e.getValue());
		}
        

	}

	private static AirQuality putInObject(StringTokenizer token) {
		// TODO Auto-generated method stub
		String Date = token.nextToken();
		String City = token.nextToken();
		String CO = token.nextToken();
		double NO2 = Double.parseDouble(token.nextToken());
		double SO2 = Double.parseDouble(token.nextToken());
		double O3 = Double.parseDouble(token.nextToken());
		double PM2 = Double.parseDouble(token.nextToken());
		double PM10 = Double.parseDouble(token.nextToken());
		int AQI = Integer.parseInt(token.nextToken());
		
		return new AirQuality(Date,City,CO,NO2,SO2,O3,PM2,PM10,AQI);
	}
	
	

}
