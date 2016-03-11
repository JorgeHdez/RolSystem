package com.kapeks.web.controller;

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

public class AdminControllerTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule =  new EasyMockRule(this);
	
	@Mock
	View mockView;
	
	private MockMvc mockMvc;
	
	@TestSubject
	private final AdminController adminController = new AdminController();
	
	@Before
	public void setUp() {
		mockMvc = standaloneSetup(adminController).setSingleView(mockView).build();
	}
	
	@Test
	public void newUserPageTest() throws Exception {
		mockMvc.perform(get("/admin/nuevo-usuario")).andExpect(status().isOk()).andExpect(view().name("newuser"));
	}
}