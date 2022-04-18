package com.example;

import java.io.Closeable;
import java.io.IOException;

public class NewFeaturesInExceptionJava7 {
	public static void tryWithResources() {
		try (
				Resource res1 = new Resource(1); 
				Resource res2 = new Resource(2); 
				Resource res3 = new Resource(3); 
				Resource res4 = new Resource(4); 
		)
		{
			// use the resources res1 and res2
			throw new RuntimeException("Ooopps!");			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		tryWithResources();
	}
	
	public static void beforeJavaSe7() {
		Resource res1 = new Resource(1);
		Resource res2 = new Resource(2);
		try {
			// use the resources res1 and res2
			throw new RuntimeException("Ooopps!");
		} finally {
			try {
				res1.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					res2.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}

	}

}

class Resource implements Closeable {
	private final int id;

	public Resource(int id) {
		this.id = id;
	}

	@Override
	public void close() throws IOException {
		System.err.println("Closing the resource %d".formatted(id));
		throw new RuntimeException("Something is wrong while closing the resource %d".formatted(id));
	}
}