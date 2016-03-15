package com.kapeks.web.controller;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import com.kapeks.security.model.AppUser;
import com.kapeks.test.TestUtil;
import com.kapeks.web.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	UserService mockUserService;

	@Mock
	View mockView;

	private MockMvc mockMvc;

	@TestSubject
	private final AdminController adminController = new AdminController(mockUserService);

	@Before
	public void setUp() {
		mockMvc = standaloneSetup(adminController).setSingleView(mockView).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void newUserPageTest() throws Exception {
		mockMvc.perform(get("/admin/nuevo-usuario")).andExpect(view().name("newuser"));
	}

	@Test
	public void addUserSuccessPostTest() throws Exception {
		AppUser user = new AppUser();
		user.setUsername("DummyUser");
		user.setEnabled(true);

		mockUserService.addUser(user);
		EasyMock.expectLastCall();
		EasyMock.replay(mockUserService);

		mockMvc.perform(post("/admin/nuevo-usuario").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(user))).andExpect(view().name("newuser"))
				.andExpect(model().attribute("msg", "Usuario registrado"));

		EasyMock.verify(mockUserService);
	}

	@Test
	public void addUserFailurePostTest() throws Exception {
		mockMvc.perform(post("/admin/nuevo-usuario").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(view().name("newuser"))
				.andExpect(model().attribute("error", "No se pudo crear el usuario"));
	}
}