package com.apple.excecise.dependency.service;

import java.util.Set;

import com.apple.excecise.dependency.exception.ServiceException;
import com.apple.excecise.dependency.model.Program;

public interface DependencyManager {
    Set<Program> list() throws ServiceException;

    void depend(Program source, Set<Program> dest) throws ServiceException;

    Set<Program> install(Program program) throws ServiceException;

    Set<Program> remove(Program program) throws ServiceException;
}
