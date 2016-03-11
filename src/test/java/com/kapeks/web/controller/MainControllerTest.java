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

import com.kapeks.test.TestUtil;
import com.kapeks.web.model.Ability;
import com.kapeks.web.service.AbilitiesService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MainControllerTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	AbilitiesService mockAbilitiesService;

	@Mock
	View mockView;

	private MockMvc mockMvc;

	@TestSubject
	private final MainController mainController = new MainController(mockAbilitiesService);

	@Before
	public void setUp() {
		mockMvc = standaloneSetup(mainController).setSingleView(mockView).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void redirectIndexTest() throws Exception {
		mockMvc.perform(get("/")).andExpect(view().name("redirect:buscar"));
	}

	@Test
	public void menuPageTest() throws Exception {
		mockMvc.perform(get("/menu")).andExpect(view().name("menu"));
	}

	@Test
	public void searchAbilityPageTest() throws Exception {
		mockMvc.perform(get("/buscar")).andExpect(view().name("searchability"));
	}

	@Test
	public void createAbilityPageTest() throws Exception {
		mockMvc.perform(get("/crear")).andExpect(view().name("createability"));
	}

	@Test
	public void getAbilitiesTest() throws Exception {
		List<Ability> abilities = new ArrayList<Ability>();
		Ability ability = new Ability();
		ability.setName("Hab1");
		ability.setType("Fisica");
		ability.setId(1);
		abilities.add(ability);
		ability = new Ability();
		ability.setName("Hab2");
		ability.setType("Fisica");
		ability.setId(2);
		abilities.add(ability);

		EasyMock.expect(mockAbilitiesService.getAbilities(null, 10)).andReturn(abilities);
		EasyMock.replay(mockAbilitiesService);

		mockMvc.perform(post("/abilities").contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Hab1")))
				.andExpect(jsonPath("$[0].type", is("Fisica")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Hab2")))
				.andExpect(jsonPath("$[1].type", is("Fisica")))
				.andExpect(content().contentTypeCompatibleWith("application/json"));
		
		EasyMock.verify(mockAbilitiesService);
	}
}