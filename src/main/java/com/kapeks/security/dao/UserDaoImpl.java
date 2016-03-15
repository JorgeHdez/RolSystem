package com.kapeks.security.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kapeks.security.model.AppUser;

@Repository
public class UserDaoImpl implements UserDao {
	private static SessionFactory factory;

	public UserDaoImpl() {
		factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
	}

	@Override
	public AppUser getUser(String username) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(AppUser.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<AppUser> users = criteria.list();
		transaction.commit();
		session.close();

		if (!users.isEmpty())
			return users.get(0);

		return null;
	}

	@Override
	public void addUser(AppUser user) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteUser(int id) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		AppUser user = session.load(AppUser.class, id);
		if (user != null) {
			session.delete(user);
		}
		transaction.commit();
		session.close();
	}
}