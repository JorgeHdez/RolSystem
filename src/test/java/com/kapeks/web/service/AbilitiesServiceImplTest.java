package com.kapeks.web.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import com.kapeks.web.dao.AbilitiesDao;
import com.kapeks.web.model.Ability;
import com.kapeks.web.model.Effect;

public class AbilitiesServiceImplTest extends EasyMockSupport {
	private Ability abilityDummy;

	public AbilitiesServiceImplTest() {
		Map<String, Effect> effectsDummy = new TreeMap<String, Effect>();

		Effect effect1 = new Effect();
		effect1.setId(1);
		effect1.setLevel("Aprendiz");
		effect1.setCost(0);
		effect1.setDescription("Wololo");

		Effect effect2 = new Effect();
		effect2.setId(2);
		effect2.setLevel("Capaz");
		effect2.setCost(3);
		effect2.setDescription("Yeiii");

		effectsDummy.put(effect1.getLevel(), effect1);
		effectsDummy.put(effect2.getLevel(), effect2);

		abilityDummy = new Ability();
		abilityDummy.setId(1);
		abilityDummy.setName("Wololo on the sky");
		abilityDummy.setCreator("DazelHirae");
		abilityDummy.setDifficulty(3);
		abilityDummy.setType("Fisica");
		abilityDummy.setDescription("Testing");
		abilityDummy.setEffects(effectsDummy);
	}

	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	private AbilitiesDao abilitiesDaoMock;

	@TestSubject
	private final AbilitiesService abilitiesService = new AbilitiesServiceImpl(abilitiesDaoMock);

	@Test
	public void getAbilityTest() {
		EasyMock.expect(abilitiesDaoMock.getAbility(1)).andReturn(abilityDummy);
		EasyMock.expect(abilitiesDaoMock.getAbility(2)).andReturn(null);
		EasyMock.replay(abilitiesDaoMock);

		assertEquals("Ability 1 Test", abilityDummy, abilitiesService.getAbility(1));
		assertEquals("Ability 2 Test", null, abilitiesService.getAbility(2));
	}

	@Test
	public void setAbilityTest() {
		EasyMock.expect(abilitiesDaoMock.getAbility(1)).andReturn(abilityDummy);
		abilitiesDaoMock.updateAbility(abilityDummy);
		EasyMock.expectLastCall();
		EasyMock.expect(abilitiesDaoMock.getAbility(2)).andReturn(null);
		abilitiesDaoMock.createAbility(abilityDummy);
		EasyMock.expectLastCall();
		EasyMock.replay(abilitiesDaoMock);
		abilitiesService.setAbility(abilityDummy);
		abilityDummy.setId(2);
		abilitiesService.setAbility(abilityDummy);
		EasyMock.verify(abilitiesDaoMock);
	}

	@Test
	public void deleteAbility() {
		EasyMock.expect(abilitiesDaoMock.getAbility(1)).andReturn(abilityDummy);
		EasyMock.expect(abilitiesDaoMock.getAbility(2)).andReturn(null);
		abilitiesDaoMock.deleteAbility(1);
		EasyMock.expectLastCall();
		EasyMock.replay(abilitiesDaoMock);
		abilitiesService.deleteAbility(2);
		abilitiesService.deleteAbility(1);
		EasyMock.verify(abilitiesDaoMock);
	}

	@Test
	public void getAbilities() {
		List<Ability> abilitiesDummy = new ArrayList<Ability>();
		abilitiesDummy.add(abilityDummy);

		EasyMock.expect(abilitiesDaoMock.getAbilities(null, 10)).andReturn(abilitiesDummy).times(2);
		EasyMock.expect(
				abilitiesDaoMock.getAbilities(EasyMock.aryEq(new String[] { "on", "the", "water" }), EasyMock.eq(3)))
				.andReturn(new ArrayList<Ability>());
		EasyMock.replay(abilitiesDaoMock);

		assertEquals("Size", 1, abilitiesService.getAbilities(null, 0).size());
		assertEquals("Ability", abilitiesDummy, abilitiesService.getAbilities(null, 0));
		assertEquals("Filtered", new ArrayList<Ability>(), abilitiesService.getAbilities("on   the water", 3));

		EasyMock.verify(abilitiesDaoMock);
	}
}
