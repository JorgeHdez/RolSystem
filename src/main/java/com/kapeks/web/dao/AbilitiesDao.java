package com.kapeks.web.dao;

import java.util.List;

import com.kapeks.web.model.Ability;

public interface AbilitiesDao {
	public List<Ability> getAbilities(String[] searchTerms, int limit);
	public Ability getAbility(int id);
	public void createAbility(Ability ability);
	public void updateAbility(Ability ability);
	public void deleteAbility(int id);
}
