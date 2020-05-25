package com.apple.exercise.dependency.service;

import java.util.Set;

import com.apple.exercise.dependency.exception.ServiceException;
import com.apple.exercise.dependency.model.Program;

public interface DependencyManager {
    Set<Program> list() throws ServiceException;

    void depend(Program source, Set<Program> dest) throws ServiceException;

    Set<Program> install(Program program) throws ServiceException;

    Set<Program> remove(Program program) throws ServiceException;
}
