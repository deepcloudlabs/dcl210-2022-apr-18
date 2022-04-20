package com.example;

import java.util.concurrent.Executors;

public class StudyThreadPools {

	public static void main(String[] args) {
		Executors.newFixedThreadPool(8);
		Executors.newSingleThreadExecutor();
		Executors.newCachedThreadPool();
		Executors.newScheduledThreadPool(8);
		Executors.newWorkStealingPool();
		

	}

}
