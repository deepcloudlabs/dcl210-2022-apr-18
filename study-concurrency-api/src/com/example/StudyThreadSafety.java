package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyThreadSafety {

	public static void main(String[] args) {
		var task = new SharedTask2();
		var threads = List.of(
		   new Thread(task),new Thread(task),new Thread(task)
		);
		threads.forEach(Thread::start);
		threads.forEach( thread -> {try { thread.join();} catch (InterruptedException e) {} } );
		System.out.println(task.getCounter());
        
	}

}

class SharedTask implements Runnable {
	private int counter;

	@Override
	public void run() {
		for (var i = 0; i < 100_000; ++i) {
			synchronized (this) { // Lock-based
				counter++;
			}
		}
	}

	public int getCounter() {
		return counter;
	}

}

// Lock-Free
class SharedTask2 implements Runnable {
	private AtomicInteger counter = new AtomicInteger();
	
	@Override
	public void run() {
		for (var i = 0; i < 100_000; ++i) {
				counter.incrementAndGet();
		}
	}
	
	public int getCounter() {
		return counter.get();
	}
	
}
