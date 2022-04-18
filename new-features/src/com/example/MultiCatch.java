package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MultiCatch {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(new File("src", "data.csv"));
			TimeUnit.MILLISECONDS.sleep(12);
		} catch (IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}

	}

}
