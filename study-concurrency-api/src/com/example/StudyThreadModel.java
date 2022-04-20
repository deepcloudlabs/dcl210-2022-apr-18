package com.example;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

public class StudyThreadModel {
	// -Xms2g -Xmx5g -Xss4m 1024 Thread -> 4g
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("%s is running the main function.".formatted(Thread.currentThread().getName()));
		var threadPool = Executors.newFixedThreadPool(8);
		var task = new Task();
		var futureTask = new FutureTask<>(task);
		threadPool.submit(futureTask);
        System.out.println(futureTask.get());
        threadPool.shutdown();
	}

}

class Task implements Callable<List<Integer>> {

	@Override
	public List<Integer> call() throws Exception {
		System.out.println("%s is running the Task.".formatted(Thread.currentThread().getName()));
		return ThreadLocalRandom.current().ints(1, 60).distinct().limit(6).sorted().boxed().toList();
	}
	
}