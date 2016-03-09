package com.kapeks.web.service;

import java.util.List;

import com.kapeks.web.model.Ability;

public interface AbilitiesService {
	public List<Ability> getAbilities(String searchTerms, int limit);
	public Ability getAbility(int id);
	public void setAbility(Ability ability);
	public void deleteAbility(int id);
}