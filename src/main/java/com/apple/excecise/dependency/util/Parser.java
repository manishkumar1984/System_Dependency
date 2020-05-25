package com.apple.excecise.dependency.util;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class Parser {

	public static String[] parse(String commandString) {
		return commandString.split("\\s+");
	}

}
