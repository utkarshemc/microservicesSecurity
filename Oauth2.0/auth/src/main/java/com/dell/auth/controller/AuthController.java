/**
 * 
 */
package com.dell.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bhardu
 * @Since Apr 27, 2020
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping("/")
	public String Welcome() {
		return ("Welcome to auth service.");
	}

	@GetMapping(value = "/private")
	public String privateArea() {
		return "Private area";
	}

	//Return Back The User Details for Oauth
	@GetMapping(value = "/validateUser")
	public Principal user(Principal user) {
		return user;
	}

}
