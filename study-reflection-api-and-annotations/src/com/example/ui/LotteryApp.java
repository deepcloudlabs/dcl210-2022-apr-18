package com.example.ui;

import com.example.model.LotteryViewModel;
import com.example.service.StandardRandomNumberGenerator;

public class LotteryApp {

	public static void main(String[] args) {
		var randomNumberGenerator = new StandardRandomNumberGenerator();
		var model = new LotteryViewModel();
		randomNumberGenerator.generateRandomNumbers(model);
		System.out.println(model.getNumbers());
	}

}
