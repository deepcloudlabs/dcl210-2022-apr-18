package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

public class StandardRandomNumberGenerator {
	public void generateRandomNumbers(Object o) {
		var clazz = o.getClass();
		for (var field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				var randomNumber = field.getAnnotation(RandomNumber.class);
				var numbers = ThreadLocalRandom.current().ints(randomNumber.min(), randomNumber.max());
				if (randomNumber.distinct())
					numbers = numbers.distinct();
				numbers = numbers.limit(randomNumber.size());
				if (randomNumber.sorted())
					numbers = numbers.sorted();
				var lottteryNumbers = numbers.boxed().toList();
				try {
					field.setAccessible(true);
					field.set(o, lottteryNumbers);
					field.setAccessible(false);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
}
