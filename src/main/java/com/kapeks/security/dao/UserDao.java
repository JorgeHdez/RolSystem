package com.kapeks.security.dao;

import com.kapeks.security.model.AppUser;

public interface UserDao {
	public AppUser getUser(String username);
}