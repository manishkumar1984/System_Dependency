package com.apple.excecise.dependency.core;

import com.apple.excecise.dependency.exception.CommandException;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public interface Command {
	String execute(String[] args) throws CommandException;
}
