package com.example;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

public class StudyThreadModel {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("%s is running the main function.".formatted(Thread.currentThread().getName()));
		var task = new Task();
		var futureTask = new FutureTask<>(task);
		var thread = new Thread(futureTask, "elma");
		thread.start();
        System.out.println(futureTask.get());
	}

}

class Task implements Callable<List<Integer>> {

	@Override
	public List<Integer> call() throws Exception {
		System.out.println("%s is running the Task.".formatted(Thread.currentThread().getName()));
		return ThreadLocalRandom.current().ints(1, 60).distinct().limit(6).sorted().boxed().toList();
	}
	
}