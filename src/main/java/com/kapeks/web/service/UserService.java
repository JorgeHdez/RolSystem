package com.kapeks.web.service;

import java.security.NoSuchAlgorithmException;

public interface UserService {
	public void addUser(String username, String password, boolean admin) throws NoSuchAlgorithmException;
	public void deleteUser(int id);
}