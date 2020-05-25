package com.apple.exercise.dependency.command;

import com.apple.exercise.dependency.core.Command;
import com.apple.exercise.dependency.exception.CommandException;
import com.apple.exercise.dependency.exception.ServiceException;
import com.apple.exercise.dependency.service.DependencyManager;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public abstract class CommandBase implements Command {

	protected DependencyManager dependencyManager;

	@Override
	public String execute(String[] args) throws CommandException {
		try {
			validateCommand(args);
			return executeCommand(args);
		} catch (CommandException | ServiceException e) {
			throw new CommandException(e.getMessage());
		}
	}

	public void setDependencyManager(DependencyManager dependencyManager) {
		this.dependencyManager = dependencyManager;
	}

	protected abstract String executeCommand(String[] args) throws CommandException, ServiceException;

	protected abstract void validateCommand(String[] args) throws CommandException;

	protected void validateArgs(String[] args, int count) throws CommandException {
		if (args.length != count) {
			throw new CommandException(
					String.format("Invalid arguments, expected (%d) received (%d)", count, args.length));
		}
	}

	protected void validateMinArgs(String[] args, int count) throws CommandException {
		if (args.length < count) {
			throw new CommandException(
					String.format("Invalid arguments, expected at least (%d) received (%d)", count, args.length));
		}
	}
}