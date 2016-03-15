package com.kapeks.web.service;

import com.kapeks.security.model.AppUser;
import com.kapeks.util.AppUtil;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapeks.security.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(AppUser user) throws NoSuchAlgorithmException {
		user.setPassword(AppUtil.hashMD5(user.getPassword()));
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}
}