package com.example;

import java.util.concurrent.TimeUnit;

import com.example.service.BusinessService;

public class BusinessApplication {

	public static void main(String[] args) {
		System.out.println("Application is running.");
		var businessService = new BusinessService();
		businessService.fun().thenAccept(result -> System.err.println(result));
		System.out.println("Application is done.");
		for (var i = 0; i < 10; ++i)
			try {
				System.err.println("Working hard %d.".formatted(i));
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
			}
	}

}
