package com.example;

import java.util.Objects;

public class Ticker extends Object {
	private String symbol;
	private double price;

	public Ticker() {
	}

	public Ticker(String symbol, double price) {
		this.symbol = symbol;
		this.price = price;
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
	public int hashCode() {
		return Objects.hash(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticker other = (Ticker) obj;
		return Objects.equals(symbol, other.symbol);
	}

	@Override
	public String toString() {
		return "TickerDocument [symbol=" + symbol + ", price=" + price + "]";
	}

}
