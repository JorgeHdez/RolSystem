package com.kapeks.security.model;

import java.util.HashSet;
import java.util.Set;

public class AppUser {
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public AppUser() {
	}

	public AppUser(boolean admin) {
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRoles.add(userRole);

		if (admin) {
			userRole = new UserRole();
			userRole.setRole("ROLE_ADMIN");
			userRoles.add(userRole);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}