package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberService;

public class LotteryApplication {

	public static void main(String[] args) {
		var lotteryService = new StandardLotteryService();
		var randomNumberServices = ServiceLoader.load(RandomNumberService.class);
		var randomNumberService = randomNumberServices.findFirst().get();
		lotteryService.setRandomNumberService(randomNumberService);
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
