package com.apple.exercise.dependency.core;

import com.apple.exercise.dependency.exception.CommandException;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public interface Command {
	String execute(String[] args) throws CommandException;
}
