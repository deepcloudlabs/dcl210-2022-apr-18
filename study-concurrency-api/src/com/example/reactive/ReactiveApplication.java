package com.example.reactive;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveApplication {

	public static void main(String[] args) {
		var events = List.of(
				   new TradeEvent("orcl", 123, 1_000),
				   new TradeEvent("ibm", 124, 2_000),
				   new TradeEvent("msft", 125, 3_000),
				   new TradeEvent("orcl", 126, 4_000),
				   new TradeEvent("ibm", 127, 5_000),
				   new TradeEvent("msft", 128, 6_000)
				);
		System.err.println("Application has just started.");
		try(var publisher = new SubmissionPublisher<TradeEvent>()){
			publisher.subscribe(new AlgoTrader());
			publisher.subscribe(new SignalProducer());
			events.forEach(publisher::submit);
			try {TimeUnit.SECONDS.sleep(60);} catch (InterruptedException e) {}		
			System.err.println("Application is done.");
		}
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("AlgoTrader has subscribed.");
		this.subscription = subscription;
		subscription.request(10);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {}
		System.err.println("AlgoTrader has received an event %s.".formatted(event));
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("AlgoTrader has failed: %s".formatted(throwable));
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader has been completed.");
	}
	
}

class SignalProducer implements Flow.Subscriber<TradeEvent> {
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SignalProducer has subscribed.");
		this.subscription = subscription;
		subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProducer has received an event %s.".formatted(event));
		subscription.request(1);
	}
	
	@Override
	public void onError(Throwable throwable) {
		System.err.println("SignalProducer has failed: %s".formatted(throwable));
	}
	
	@Override
	public void onComplete() {
		System.err.println("SignalProducer has been completed.");
	}
	
}