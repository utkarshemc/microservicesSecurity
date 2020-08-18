/**
 * 
 */
package com.dell.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dell.auth.entity.User;
import com.dell.auth.model.MyUserDetails;
import com.dell.auth.repository.UserRepository;

/**
 * @author bhardu
 * @Since Apr 27, 2020
 */
@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		return new MyUserDetails(user);
	}

}
