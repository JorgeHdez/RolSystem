package com.kapeks.web.service;

import java.security.NoSuchAlgorithmException;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import com.kapeks.security.dao.UserDao;
import com.kapeks.security.model.AppUser;

public class UserServiceImplTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	UserDao mockUserDao;

	@TestSubject
	private final UserService userService = new UserServiceImpl(mockUserDao);

	@Test
	public void addAdminSuccessTest() throws NoSuchAlgorithmException {
		AppUser user = new AppUser(true);
		user.setUsername("dummy");
		user.setPassword("wololo");

		EasyMock.expect(mockUserDao.getUser("dummy")).andReturn(null);
		mockUserDao.addUser(user);
		EasyMock.expectLastCall();
		EasyMock.replay(mockUserDao);
		userService.addUser("dummy", "wololo", true);
		EasyMock.verify(mockUserDao);
	}

	@Test
	public void addUserSuccessTest() throws NoSuchAlgorithmException {
		AppUser user = new AppUser(false);
		user.setUsername("dummy");
		user.setPassword("wololo");

		EasyMock.expect(mockUserDao.getUser("dummy")).andReturn(null);
		mockUserDao.addUser(user);
		EasyMock.expectLastCall();
		EasyMock.replay(mockUserDao);
		userService.addUser("dummy", "wololo", false);
		EasyMock.verify(mockUserDao);
	}

	@Test
	public void addUserFailureTest() throws NoSuchAlgorithmException {
		AppUser user = new AppUser(false);
		user.setUsername("dummy2");
		user.setPassword("wololo");

		EasyMock.expect(mockUserDao.getUser("dummy2")).andReturn(user);
		EasyMock.replay(mockUserDao);
		userService.addUser("dummy2", "wololo", false);
		EasyMock.verify(mockUserDao);
	}

	@Test
	public void deleteUserSuccessTest() {
		mockUserDao.deleteUser(2);
		EasyMock.expectLastCall();
		EasyMock.replay(mockUserDao);
		userService.deleteUser(2);
		EasyMock.verify(mockUserDao);
	}
}