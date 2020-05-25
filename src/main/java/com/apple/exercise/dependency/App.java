package com.apple.exercise.dependency;

import java.io.File;
import java.util.Scanner;

import com.apple.exercise.dependency.core.CommandFactory;
import com.apple.exercise.dependency.exception.CommandException;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */

public class App {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new Exception("Error: Input file path not specified");
		}

		CommandFactory cf = CommandFactory.init();
		String input = App.class.getClassLoader().getResource(args[0]).getFile();
		Scanner scanner = new Scanner(new File(input));

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			System.out.println(line);
			String[] arguments = parse(line);
			try {
				String response = cf.execute(arguments);
				if (response != null) {
					System.out.print(response);
				}
			} catch (CommandException e) {
				System.out.println(e.getMessage());
			}
		}

		scanner.close();

	}

	private static String[] parse(String commandString) {
		return commandString.split("\\s+");
	}
}
