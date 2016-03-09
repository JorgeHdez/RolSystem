package com.kapeks.security.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import com.kapeks.security.dao.UserDao;
import com.kapeks.security.model.AppUser;
import com.kapeks.security.model.UserRole;

public class UserDetailsServiceImplTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	private UserDao userDaoMock;

	@TestSubject
	private final UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl(userDaoMock);

	@Test
	public void loadUserByUsernameTest() {
		UserRole role = new UserRole();
		role.setId(1);
		role.setRole("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(role);
		AppUser user = new AppUser();
		user.setEnabled(true);
		user.setUsername("DazelHirae");
		user.setPassword("b2456aade42790ff28bcda05af0b2a47");
		user.setUserRoles(userRoles);

		EasyMock.expect(userDaoMock.getUser("DazelHirae")).andReturn(user);
		EasyMock.expect(userDaoMock.getUser("jhernan")).andReturn(null);
		EasyMock.replay(userDaoMock);

		assertEquals("Exists", User.class, userDetailsServiceImpl.loadUserByUsername("DazelHirae").getClass());
		assertEquals("Non-exists", null, userDetailsServiceImpl.loadUserByUsername("jhernan"));
	}
}