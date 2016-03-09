package com.kapeks.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapeks.web.dao.AbilitiesDao;
import com.kapeks.web.model.Ability;

@Service
public class AbilitiesServiceImpl implements AbilitiesService {
	AbilitiesDao abilitiesDao;

	@Autowired
	public AbilitiesServiceImpl(AbilitiesDao abilitiesDao) {
		this.abilitiesDao = abilitiesDao;
	}

	/*
	 * Call for a list of abilities based on Search Terms and a limit for the
	 * returned list (if limit is zero the limit will be set to 10).
	 */
	@Override
	public List<Ability> getAbilities(String searchTerms, int limit) {
		String[] filterWords = null;
		if (searchTerms != null) {
			searchTerms = searchTerms.replaceAll("\\s+", " ");
			filterWords = searchTerms.split(" ");
		}

		if (limit <= 0)
			limit = 10;

		return abilitiesDao.getAbilities(filterWords, limit);
	}

	/*
	 * Call for an ability based on an id.
	 */
	@Override
	public Ability getAbility(int id) {
		return abilitiesDao.getAbility(id);
	}

	/*
	 * Checks if the ability already exists, if false create a new ability,
	 * otherwise updates the existing ability.
	 */
	@Override
	public void setAbility(Ability ability) {
		if (abilitiesDao.getAbility(ability.getId()) != null)
			abilitiesDao.updateAbility(ability);
		else
			abilitiesDao.createAbility(ability);
	}

	/*
	 * Checks if the ability already exists, if true deletes it.
	 */
	@Override
	public void deleteAbility(int id) {
		if (abilitiesDao.getAbility(id) != null)
			abilitiesDao.deleteAbility(id);
	}
}