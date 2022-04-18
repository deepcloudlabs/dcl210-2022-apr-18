package com.example;

public class SwitchInJavaSE12 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int weekDay = 3;
		String status;
		switch (weekDay) {
		case 1,2,3,4,5:
			status = "working";
			break;
		case 6, 7:
			status = "resting";
			break;
		default:
			status = "unknown";
		}
		status = switch (weekDay) {
			case 1, 2, 3, 4, 5 -> {
				yield "working";
			}
			case 6, 7 -> {
				yield "resting";
			}
			default -> {
				yield "unknown";
			}
		};

	}

}
