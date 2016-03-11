package com.kapeks.security.dao;

import static org.junit.Assert.assertEquals;

import org.easymock.TestSubject;
import org.junit.Test;


public class UserDaoImplTest {
	@TestSubject
	private final UserDao userDao = new UserDaoImpl();

	@Test
	public void getUserWhenUsernameExistsTest() {
		assertEquals("Don't exists", null, userDao.getUser("DummyName"));
		assertEquals("Confirm existence", "Tester", userDao.getUser("Tester").getUsername());
	}
}