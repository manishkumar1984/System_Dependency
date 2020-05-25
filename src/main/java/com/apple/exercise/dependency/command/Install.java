package com.apple.excecise.dependency.command;

import java.util.Set;

import com.apple.excecise.dependency.exception.CommandException;
import com.apple.excecise.dependency.exception.ServiceException;
import com.apple.excecise.dependency.model.Program;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */

public class Install extends CommandBase {

	private static final int ARGS_COUNT = 2;

	@Override
	protected String executeCommand(String[] args) throws CommandException, ServiceException {
		Set<Program> installed = dependencyManager.install(new Program(args[1]));

		StringBuilder output = new StringBuilder();
		for (Program program : installed) {
			output.append("Installing ").append(program.getName()).append("\n");
		}

		return output.toString();
	}

	@Override
	protected void validateCommand(String[] args) throws CommandException {
		validateArgs(args, ARGS_COUNT);
	}
}
