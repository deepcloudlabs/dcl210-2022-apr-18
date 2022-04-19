package com.example;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class Exercise01 {

	public static void main(String[] args) {
		var numbers = IntStream.range(1, 100).boxed().toList();
		var sum = 0;
		// external loop
		for (var number : numbers) {
			if (number % 2 == 0) {
				var squared = number * number;
				sum += squared;
			}
		}
		System.out.println("sum=%d".formatted(sum));

		// C# LINQ
		// C++ Ranges
		// Java Stream API

		// Collection API -> Stream API -> Filter/Map/Reduce -> Functional Programming
		// Function i. higher-order function: filter, map
		// ii. pure function : ifEven, toSquare -> a) lambda expression b) method reference
		// Java: Function -> Functional Interface -> SAM
		Predicate<Integer> ifEven = t -> t % 2 == 0;
		ToIntFunction<Integer> toSquare = u -> { 
											return u*u; 
										  } ;
		              // internal loop
		sum = numbers.stream() // Lazy Evaluation
				     .parallel() // Fork/Join Framework -> Task/Job Stealing Algorithm 
				     .peek(System.err::println)
				     .filter(ifEven)
				     .peek(System.err::println)
				     .mapToInt(toSquare)
				     .peek(System.err::println)
				     .sequential()
				     .sum(); // Terminal Function
		System.out.println("sum=%d".formatted(sum));
	}

}
