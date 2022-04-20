package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

public class LotteryApplication {

	public static void main(String[] args) {
		var lotteryService = new StandardLotteryService();
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(new File("src","application.properties")));
			var qualityLevel = QualityLevel.valueOf(props.getProperty("quality.level"));
			
			RandomNumberService.getRandomNumberService(
				ServiceLoader.load(RandomNumberService.class).stream().toList(), qualityLevel)
			 .ifPresent( randomNumberService -> {
				lotteryService.setRandomNumberService(randomNumberService);
				lotteryService.draw(60, 6, 10)
				.forEach(System.out::println);			
			 });
			
		}catch (IOException e) {
		}	
		getRandomNumberService().ifPresent( randomNumberService -> {
			lotteryService.setRandomNumberService(randomNumberService);
			lotteryService.draw(60, 6, 10)
			              .forEach(System.out::println);			
		});
		System.err.println("Application is done!");
	}

	private static Optional<RandomNumberService> getRandomNumberService() {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(new File("src","application.properties")));
			var qualityLevel = QualityLevel.valueOf(props.getProperty("quality.level"));
			return ServiceLoader.load(RandomNumberService.class)
			             .stream()
			             .map(Provider::get)
			             .filter( randomNumberService -> {
			            	 var clazz = randomNumberService.getClass();
			            	 if (clazz.isAnnotationPresent(ServiceQuality.class)) {
			            		 var serviceQuality = clazz.getAnnotation(ServiceQuality.class);
			            		 return qualityLevel.equals(serviceQuality.value());
			            	 }
			            	 return false;
			             })
			             .findFirst();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return Optional.empty();
	}

}
