package com.company.JunghoonYoonU1Capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JunghoonYoonU1CapstoneApplication {

	public List<Integer> averageList(List<List<Integer>> list){
//		List<Integer> newlist = new ArrayList<>();
//		double sum = 0;
//		for (List<Integer> newList : list) {
//			int average = list.size();
//			newList.addAll();
//
//
//		}
//		double average = sum / list.size();
				List<Integer> aList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			int averageNumber = list.size();
			for(int j = 0; j < i; j++) {
				int sum = 0;
				sum += (j / averageNumber);
				aList.add((sum));

			}
		}
		return aList;
	}




//		for(int i = 0; i < list.size(); i++) {
//			for(int j = 0; j < i; j++) {
//				int average = 5;
//				average += (j/list.size());
//				averageList.add(average);
//			}
//		}
//		return averageList;


	public static void main(String[] args) throws IOException {
		SpringApplication.run(JunghoonYoonU1CapstoneApplication.class, args);
	}
}
