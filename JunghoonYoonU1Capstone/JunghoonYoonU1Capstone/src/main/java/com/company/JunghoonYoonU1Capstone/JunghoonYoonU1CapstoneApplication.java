package com.company.JunghoonYoonU1Capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JunghoonYoonU1CapstoneApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JunghoonYoonU1CapstoneApplication.class, args);
//		HashMap<String, String> statesCode = new HashMap<>();
//		String filePath = "states.rtf";
//		try {
//			String line;
//			BufferedReader reader = new BufferedReader(new FileReader(filePath));
//			while((line = reader.readLine()) != null) {
//				int spaceIndex = line.indexOf(":");
//				line = line.substring(0, spaceIndex);
//				String[] parts = line.split("-", 2);
//				if(parts.length >= 2) {
//					String key = parts[0];
//					String value = parts[1];
//					statesCode.put(key, value);
//				} else {
//					System.out.println("Ignoring line: " + line);
//				}
//			}
//
//		for(String key: statesCode.keySet()) {
//			System.out.println(key + ":" + statesCode.get(key));
//			reader.close();
//		}
//		} catch (FileNotFoundException e) {
//			System.out.println(e);
//		}
	}

}
