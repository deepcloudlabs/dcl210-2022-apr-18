package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class BusinessService {
	public int doBusiness() { // synchronous method -> blocking
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
		}
		return 42;
	}

	public CompletableFuture<Integer> fun() { // asynchronous method
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("%s runs fun() async method".formatted(Thread.currentThread().getName()));
			try {
				TimeUnit.SECONDS.sleep(7);
			} catch (Exception e) {
			}
			return 42;
		});
	}
}
