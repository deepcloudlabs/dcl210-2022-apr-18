package com.example;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;

public class ListAllProcesses {

	public static void main(String[] args) {
		Consumer<String> printCommand =
				command -> {
					var path = Paths.get(command);
					System.out.println(path.getFileName());
				};
		ProcessHandle.allProcesses()
		             .filter( process -> process.info().command().isPresent())
		             .map(process -> process.info().command())
		             .map(Optional::get)
		             .distinct()
		             .sorted()
		             .forEach(printCommand);

	}

}
