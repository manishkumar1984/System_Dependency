package com.apple.excecise.dependency.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class Program {
    private String name;
    private Set<Program> dependencies;

    public Program(String name) {
        this.name = name;
        dependencies = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Program> getDependencies() {
        return Collections.unmodifiableSet(dependencies);
    }

    public boolean addDependency(Program program) {
        return dependencies.add(program);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Program program = (Program) other;

        return name.equals(program.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static Set<Program> asProgram(String[] programs) {
        Set<Program> set = new LinkedHashSet<>(programs.length);
        for (String name : programs) {
            set.add(new Program(name));
        }
        return set;
    }
}
