/**
 * 
 */
package com.dell.auth.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dell.auth.entity.User;

/**
 * @author bhardu
 * @Since Apr 27, 2020
 */
public class MyUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean active;
	private int id;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.userName = user.getUsername();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.id = user.getId();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public MyUserDetails(String username) {

		this.userName = username;
	}

	public MyUserDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return active;
	}

	public int getUserId() {
		return id;
	}

	public void setUserId(int userId) {
		this.id = userId;
	}

}
