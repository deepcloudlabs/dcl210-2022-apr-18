package com.example.lottery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickers")
public class TickerDocument {
	@Id
	private String symbol;
	private double price;

	public TickerDocument() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TickerDocument [symbol=" + symbol + ", price=" + price + "]";
	}

}
