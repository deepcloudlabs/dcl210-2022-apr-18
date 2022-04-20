package com.example.binance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyAsyncBinanceRestApi {
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws Exception {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		long start = System.currentTimeMillis();
		for (var i = 0; i < 10; i++) {
			httpClient.sendAsync(request, BodyHandlers.ofString())
			          .thenAcceptAsync(response -> {
			        	  var count = counter.incrementAndGet();
			        	  if (count == 10) {
			        		  long stop = System.currentTimeMillis();
			        		  System.out.println("Duration: %d ms.".formatted(stop-start));			        		  
			        	  }
			        	  System.out.println(response.body());
			          });		
		}
		try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) {}		

	}

}
