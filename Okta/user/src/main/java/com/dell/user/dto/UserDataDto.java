/**
 * 
 */
package com.dell.user.dto;

/**
 * @author bhardu
 * @Since Apr 23, 2020
 */
public class UserDataDto {

	private String username;
	private String password;
	private String roles;
	private boolean active;

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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
