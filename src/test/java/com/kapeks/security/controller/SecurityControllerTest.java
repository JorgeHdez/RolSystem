package com.kapeks.security.controller;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SecurityControllerTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	View mockView;

	private MockMvc mockMvc;

	@TestSubject
	private final SecurityController securityController = new SecurityController();

	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(securityController).setSingleView(mockView).build();
	}

	@Test
	public void loginPageSimpleTest() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	public void loginUserPassErrorTest() throws Exception {
		mockMvc.perform(get("/login").param("error", "error")).andExpect(status().isOk())
				.andExpect(view().name("login"))
				.andExpect(model().attribute("error", "Usuario o contraseña incorrectos"));
	}

	@Test
	public void logoutTest() throws Exception {
		mockMvc.perform(get("/login").param("logout", "logout")).andExpect(status().isOk())
				.andExpect(view().name("login")).andExpect(model().attribute("msg", "Te has desconectado"));
	}
	
	@Test
	public void deniedTest() throws Exception {
		mockMvc.perform(get("/denegado")).andExpect(status().isOk()).andExpect(view().name("denied"));
	}
}