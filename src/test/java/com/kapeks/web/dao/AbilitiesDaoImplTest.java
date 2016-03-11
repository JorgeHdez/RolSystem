package com.kapeks.web.dao;

import static org.junit.Assert.assertEquals;

import org.easymock.TestSubject;
import org.junit.Test;

public class AbilitiesDaoImplTest {
	@TestSubject
	private final AbilitiesDao abilitiesDao = new AbilitiesDaoImpl();
	
	@Test
	public void getAbilityTest() {
		assertEquals("Get existing ability", "Testington", abilitiesDao.getAbility(2).getName());
		assertEquals("Ability don't exists", null, abilitiesDao.getAbility(0));
	}

	@Test
	public void createAbilityTest() {
	}

	@Test
	public void updateAbilityTest() {
	}

	@Test
	public void deleteAbilityTest() {
	}
	
	@Test
	public void getAbilitiesTest() {
	}
}