package com.example;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class HowtoCreateProcess {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		do {
			var process = Runtime.getRuntime().exec("mspaint");
			var processHandle = process.toHandle();
			processHandle.onExit().get();
			System.out.println("Calculator is down. Re-running the calculator!");
		} while (true);
	}
}
