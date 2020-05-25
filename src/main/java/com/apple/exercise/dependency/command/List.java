package com.apple.excecise.dependency.command;

import java.util.Set;

import com.apple.excecise.dependency.exception.CommandException;
import com.apple.excecise.dependency.exception.ServiceException;
import com.apple.excecise.dependency.model.Program;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class List extends CommandBase {

	private static final int ARGS_COUNT = 1;

	@Override
	public String executeCommand(String[] args) throws CommandException, ServiceException {

		Set<Program> installed = dependencyManager.list();

		StringBuffer response = new StringBuffer();
		for (Program program : installed) {
			response.append("\t").append(program.getName()).append("\n");
		}

		return response.toString();
	}

	@Override
	protected void validateCommand(String[] args) throws CommandException {
		validateArgs(args, ARGS_COUNT);
	}
}
