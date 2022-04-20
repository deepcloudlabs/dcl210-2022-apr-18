package com.example.reactive;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class StudyLegacyObserverApp {

	public static void main(String[] args) {
		var events = List.of(
		   new TradeEvent("orcl", 123, 1_000),
		   new TradeEvent("ibm", 124, 2_000),
		   new TradeEvent("msft", 125, 3_000),
		   new TradeEvent("orcl", 126, 4_000),
		   new TradeEvent("ibm", 127, 5_000),
		   new TradeEvent("msft", 128, 6_000)
		);
		// Stream of events
		Observer slowObserver = (o,event) -> {
			try {TimeUnit.SECONDS.sleep(5);} catch (Exception e) {}
			System.err.println("Slow observer: %s.".formatted(event));
		};
		Observer fastObserver = (o,event) -> {
			System.err.println("Fast observer: %s.".formatted(event));
		};
		var tradeObservable = new TradeObservable();
		tradeObservable.addObserver(slowObserver);
		tradeObservable.addObserver(fastObserver);
		events.forEach(tradeObservable::notifyObservers);
		
	}

}

class TradeObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}