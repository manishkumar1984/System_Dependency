package com.apple.exercise.dependency.exception;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class CommandException extends Exception {

	private static final long serialVersionUID = 1622343001564824840L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
