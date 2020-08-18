/**
 * 
 */
package com.dell.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dell.auth.entity.User;
import com.dell.auth.model.AuthenticateRequest;
import com.dell.auth.model.AuthenticateResponse;
import com.dell.auth.model.MyUserDetails;
import com.dell.auth.service.MyUserDetailService;
import com.dell.auth.util.JwtUtil;

/**
 * @author bhardu
 * @Since Apr 27, 2020
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailsService;

	@Autowired
	private JwtUtil myJwtToken;

	@RequestMapping("/")
	public String Welcome() {
		return ("Welcome to auth service.");
	}

	// Authenticate the user with its username and password
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAutheticationToken(@RequestBody AuthenticateRequest authenticateRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUsername(), authenticateRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("Username or Password is incorrect", e);
		}

		final MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticateRequest.getUsername());

		String jwt = myJwtToken.generateToken(userDetails, userDetails.getAuthorities().toString());

		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

	// Can be accessed only by ADMIN
	@RequestMapping(value = "/validateAdminUrl", method = RequestMethod.GET)
	public void validateAdminUrl() {

	}

	// Can be accessed only by USER
	@RequestMapping(value = "/validateUserUrl", method = RequestMethod.GET)
	public void validateUserUrl() {

	}

	@RequestMapping(value = "/getUserByJwt/{id}", method = RequestMethod.GET)
	public String getUserByJwt(@PathVariable String id) {
		return myJwtToken.extractUsername(id);
	}
}
