package com.apple.exercise.dependency.command;

import java.util.Arrays;

import com.apple.exercise.dependency.exception.CommandException;
import com.apple.exercise.dependency.exception.ServiceException;
import com.apple.exercise.dependency.model.Program;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class Depend extends CommandBase {

	private static final int ARGS_COUNT = 3;

	@Override
	protected String executeCommand(String[] args) throws CommandException, ServiceException {
		dependencyManager.depend(new Program(args[1]), Program.asProgram(Arrays.copyOfRange(args, 2, args.length)));
		return null;
	}

	@Override
	protected void validateCommand(String[] args) throws CommandException {
		validateMinArgs(args, ARGS_COUNT);
	}
}
