package com.example;

import java.util.LinkedHashSet;

public class MemoryLeak {

	public static void main(String[] args) {
		var tickers = new LinkedHashSet<Ticker>();
		tickers.add(new Ticker("orcl", 123.0));
		System.err.println(tickers.size());
		tickers.remove(new Ticker("orcl", 123.0));
		System.err.println(tickers.size());
	}

}
