package com.example.ui;

import java.lang.reflect.Proxy;

import com.example.handler.AuditingHandler;
import com.example.handler.ProfilingHandler;
import com.example.service.LotteryService;
import com.example.service.business.StandardLotteryService;

public class LotteryApplication {

	public static void main(String[] args) {
		StandardLotteryService standardLotteryService = new StandardLotteryService();
		var clazz = standardLotteryService.getClass();
		var lotteryService = (LotteryService)
		Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(), 
				new ProfilingHandler(standardLotteryService)
		);
		lotteryService = (LotteryService)
		Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(), 
				new AuditingHandler(lotteryService)
				);
		standardLotteryService.setSelf(lotteryService);
		// jdk.proxy1.$Proxy0
		System.err.println(lotteryService.getClass().getName());
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
