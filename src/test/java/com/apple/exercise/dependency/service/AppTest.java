package com.apple.exercise.dependency.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.apple.exercise.dependency.exception.ServiceException;
import com.apple.exercise.dependency.model.Program;

public class AppTest {
	private DependencyManagerImpl manager;

	private Program a;
	private Program b;
	private Program c;
	private Program d;

	@Before
	public void setUp() {
		a = new Program("A");
		b = new Program("B");
		c = new Program("C");
		d = new Program("D");

		manager = new DependencyManagerImpl();
	}

	@Test
	public void install() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(b, toSet(c));

		Assert.assertEquals(3, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);

		Assert.assertEquals(3, manager.list().size());
		Assert.assertTrue(manager.isInstalled(a));
		Assert.assertTrue(manager.isInstalled(b));
		Assert.assertTrue(manager.isInstalled(c));
	}

	@Test(expected = ServiceException.class)
	public void cannotInstall() throws ServiceException {
		manager.depend(a, toSet(b));

		Assert.assertEquals(2, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
		manager.depend(b, toSet(c));
	}

	@Test(expected = ServiceException.class)
	public void loop() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(b, toSet(c));
		manager.depend(c, toSet(d));
		manager.depend(d, toSet(b));

		Assert.assertEquals(4, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
	}

	@Test
	public void removeSubSet() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(b, toSet(c));

		Assert.assertEquals(3, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
		Assert.assertEquals(3, manager.list().size());

		manager.remove(a);
		Assert.assertEquals(0, manager.list().size());
	}

	@Test
	public void removeLinkList() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(b, toSet(c));
		manager.depend(b, toSet(d));
		manager.depend(d, toSet(c));

		Assert.assertEquals(4, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
		Assert.assertEquals(4, manager.list().size());

		manager.remove(a);
		Assert.assertEquals(0, manager.list().size());
	}

	@Test
	public void removeKeepNeededPrograms() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(a, toSet(c));
		manager.depend(d, toSet(b));

		Assert.assertEquals(4, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
		Assert.assertEquals(3, manager.list().size());

		manager.install(d);
		Assert.assertEquals(4, manager.list().size());

		manager.remove(a);
		Assert.assertEquals(2, manager.list().size());
		Assert.assertTrue(manager.list().contains(b));
		Assert.assertTrue(manager.list().contains(d));
	}

	@Test(expected = ServiceException.class)
	public void notRemovable() throws ServiceException {
		manager.depend(a, toSet(b));
		manager.depend(b, toSet(c));

		Assert.assertEquals(3, manager.getPrograms().size());
		Assert.assertEquals(0, manager.list().size());

		manager.install(a);
		Assert.assertEquals(3, manager.list().size());

		manager.remove(b);
	}

	private Set<Program> toSet(Program... programs) {
		Set<Program> set = new HashSet<Program>(programs.length);
		Collections.addAll(set, programs);
		return set;
	}
}
