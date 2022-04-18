package com.example;

public class NewFeaturesJavaSe7 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int x = 0b1100_1010;
		int y = 1_000_000_000;
		String weekDay = "monday";
		switch (weekDay) {
		case "monday":
		case "tuesday":
		case "wednesday":
		case "thursday":
		case "friday":
			System.out.println("work hard!");
			break;
		case "saturday":
		case "sunday":
			System.out.println("have fun and rest!");
			break;
		default:
			System.err.println("no such day is available!");

		}

	}

}
