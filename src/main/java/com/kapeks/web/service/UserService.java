package com.kapeks.web.service;

import java.security.NoSuchAlgorithmException;

import com.kapeks.security.model.AppUser;

public interface UserService {
	public void addUser(AppUser user) throws NoSuchAlgorithmException;
	public void deleteUser(int id);
}