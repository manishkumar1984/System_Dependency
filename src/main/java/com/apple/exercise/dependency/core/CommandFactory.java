package com.apple.exercise.dependency.core;

import java.util.HashMap;
import java.util.Map;

import com.apple.exercise.dependency.command.Depend;
import com.apple.exercise.dependency.command.Install;
import com.apple.exercise.dependency.command.List;
import com.apple.exercise.dependency.command.Remove;
import com.apple.exercise.dependency.exception.CommandException;
import com.apple.exercise.dependency.service.DependencyManager;
import com.apple.exercise.dependency.service.DependencyManagerImpl;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 *
 */
public class CommandFactory {
	public static final String DEPEND = "DEPEND";
	public static final String INSTALL = "INSTALL";
	public static final String LIST = "LIST";
	public static final String REMOVE = "REMOVE";

	private Map<String, Command> commands;

	private CommandFactory() {
		commands = new HashMap<>();
	}

	private void addCommand(String name, Command command) {
		commands.put(name, command);
	}

	public String execute(String[] args) throws CommandException {
		Command command = commands.get(args[0]);
		if (command != null) {
			return command.execute(args);
		}
		return "Command not found!";
	}

	public static CommandFactory init() {
		CommandFactory cf = new CommandFactory();
		DependencyManager dependencyManager = new DependencyManagerImpl();

		Depend dependCommand = new Depend();
		dependCommand.setDependencyManager(dependencyManager);
		cf.addCommand(DEPEND, dependCommand);

		Install addCommand = new Install();
		addCommand.setDependencyManager(dependencyManager);
		cf.addCommand(INSTALL, addCommand);

		Remove removeCommand = new Remove();
		removeCommand.setDependencyManager(dependencyManager);
		cf.addCommand(REMOVE, removeCommand);

		List listCommand = new List();
		listCommand.setDependencyManager(dependencyManager);
		cf.addCommand(LIST, listCommand);

		return cf;
	}
}
