package com.apple.exercise.dependency.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.apple.exercise.dependency.exception.ServiceException;
import com.apple.exercise.dependency.model.Program;

public class DependencyManagerImpl implements DependencyManager {
	private Map<Program, Set<Program>> dependencies;
	private Set<Program> installed;

	public DependencyManagerImpl() {
		dependencies = new HashMap<>();
		installed = new HashSet<>();
	}

	@Override
	public void depend(Program source, Set<Program> dest) throws ServiceException {
		checkProgram(source);
		checkProgram(dest);
		checkReinstall(source);

		addDependency(source);
		for (Program program : dest) {
			addDependency(program);
			source.addDependency(program);
			dependencies.get(program).add(source);
		}
	}

	@Override
	public Set<Program> install(Program program) throws ServiceException {
		checkProgram(program);
		checkReinstall(program);
		Set<Program> installable = new HashSet<>();
		addRecursive(getProgram(program), installable);
		return installable;
	}

	@Override
	public Set<Program> list() throws ServiceException {
		return Collections.unmodifiableSet(installed);
	}

	@Override
	public Set<Program> remove(Program program) throws ServiceException {
		checkIsExisting(program);
		checkRemovable(program);
		checkIsInstalled(program);
		Set<Program> removable = new HashSet<>();
		removeRecursive(getProgram(program), removable);
		return removable;
	}

	public Set<Program> getPrograms() {
		return Collections.unmodifiableSet(dependencies.keySet());
	}

	public Program getProgram(Program program) {
		return dependencies.keySet().stream().filter(p -> p.equals(program)).findFirst().orElse(program);
	}

	private boolean isInstalled(Program program) {
		return installed.contains(program);
	}

	private void addDependency(Program program) {
		if (!dependencies.containsKey(program)) {
			dependencies.put(program, new HashSet<>());
		}
	}

	private void checkProgram(Program program) throws ServiceException {
		if (program == null) {
			throw new ServiceException("Program cannot be null !");
		}
	}
	
	private void checkProgram(Set<Program> programs) throws ServiceException {
		if (programs == null) {
			throw new ServiceException("Dependent programs cannot be null !");
		}
	}

	private void checkReinstall(Program program) throws ServiceException {
		if (isInstalled(program)) {
			throw new ServiceException(program.getName() + " is already installed.");
		}
	}

	private void addRecursive(Program program, Set<Program> installable) throws ServiceException {
		installable.add(program);

		for (Program dependency : program.getDependencies()) {
			if (!installed.contains(dependency)) {
				if (installable.contains(dependency)) {
					throw new ServiceException("Cyclic dependency when installing program.");
				}
				addRecursive(dependency, installable);
			}
		}

		installed.add(program);
	}

	private void checkIsInstalled(Program program) throws ServiceException {
		if (!installed.contains(program)) {
			throw new ServiceException(program.getName() + " is not installed.");
		}
	}

	private void checkIsExisting(Program program) throws ServiceException {
		if (!dependencies.keySet().contains(program)) {
			throw new ServiceException("Unknown Program: " + program.getName());
		}
	}

	private void checkRemovable(Program program) throws ServiceException {
		Set<Program> incoming = new HashSet<>(dependencies.get(program));
		incoming.retainAll(installed);
		if (!incoming.isEmpty()) {
			throw new ServiceException(program.getName() + " is still needed.");
		}
	}

	private void removeRecursive(Program program, Set<Program> removable) {
		removable.add(program);
		installed.remove(program);

		for (Program dependency : program.getDependencies()) {
			Set<Program> incoming = new HashSet<>(dependencies.get(dependency));
			incoming.removeAll(removable);
			incoming.retainAll(installed);
			if (incoming.isEmpty()) {
				removeRecursive(dependency, removable);
			}
		}
	}

}
