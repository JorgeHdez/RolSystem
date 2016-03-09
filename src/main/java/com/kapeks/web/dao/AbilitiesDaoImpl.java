package com.kapeks.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kapeks.web.model.Ability;

@Repository
public class AbilitiesDaoImpl implements AbilitiesDao {
	private static SessionFactory factory;

	public AbilitiesDaoImpl() {
		factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
	}

	/*
	 * Search on the database for all abilities whose name contains all the
	 * search terms (if searchTerms is empty or null it will get all abilities).
	 * The results are limited to the numbers in limit variable.
	 */
	@Override
	public List<Ability> getAbilities(String[] searchTerms, int limit) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Ability.class, "ability");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if (searchTerms != null)
			for (String term : searchTerms)
				criteria.add(Restrictions.ilike("name", term, MatchMode.ANYWHERE));

		@SuppressWarnings("unchecked")
		List<Ability> abilities = criteria.list();
		transaction.commit();
		session.close();
		return abilities;
	}

	/*
	 * Gets from the database the ability with the id number.
	 */
	@Override
	public Ability getAbility(int id) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Ability.class, "ability");
		criteria.createAlias("ability.effects", "effects");
		criteria.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<Ability> abilities = criteria.list();
		transaction.commit();
		session.close();
		
		if (!abilities.isEmpty())
			return abilities.get(0);
		
		return null;
	}

	/*
	 * Creates a new ability on the database with the information stored in the
	 * ability variable.
	 */
	@Override
	public void createAbility(Ability ability) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(ability);
		transaction.commit();
		session.close();
	}

	/*
	 * Updates on the database the ability which is equals to the one stored in
	 * the ability variable.
	 */
	@Override
	public void updateAbility(Ability ability) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Ability abilityUpdate = (Ability) session.load(Ability.class, ability.getId());
		abilityUpdate.setName(ability.getName());
		abilityUpdate.setCreator(ability.getCreator());
		abilityUpdate.setLastChange(ability.getLastChange());
		abilityUpdate.setDifficulty(ability.getDifficulty());
		abilityUpdate.setType(ability.getType());
		abilityUpdate.setDescription(ability.getDescription());
		abilityUpdate.setEffects(ability.getEffects());
		transaction.commit();
		session.close();
	}

	/*
	 * Deletes from the database the ability with the same id.
	 */
	@Override
	public void deleteAbility(int id) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Ability ability = session.load(Ability.class, id);
		if (ability != null) {
		    session.delete(ability);
		}
		transaction.commit();
		session.close();
	}
}