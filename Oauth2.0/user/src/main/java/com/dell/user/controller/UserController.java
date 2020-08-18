/**
 * 
 */
package com.dell.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dell.user.constants.UserConstant;
import com.dell.user.dto.AuthenticateRequest;
import com.dell.user.dto.AuthenticateResponse;
import com.dell.user.dto.UserDataDto;
import com.dell.user.entity.Payment;
import com.dell.user.entity.User;
import com.dell.user.exception.UserException;
import com.dell.user.service.UserService;

/**
 * @author bhardu
 * @Since Apr 23, 2020
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@GetMapping(value = "/")
	public String test() {
		return "Welcome to User Microservice";
	}

	// Any User Can do a Sign UP
	@RequestMapping(value = UserConstant.URL_SIGNUP, method = RequestMethod.POST)
	public String signUp(@RequestBody UserDataDto userDataDto) {
		try {
			User user = modelMapper.map(userDataDto, User.class);
			userService.signup(user);
			return UserConstant.SUCCESFULL_SIGNUP;
		} catch (UserException e) {
			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
	}

	// Registered User Can Login into there system
	@RequestMapping(value = UserConstant.URL_LOGIN, method = RequestMethod.POST)
	public AuthenticateResponse login(@RequestBody AuthenticateRequest authenticateRequest) {
		try {
			return userService.login(authenticateRequest);
		} catch (UserException e) {
			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
	}

	// Display list of Users to accounts which have role as ADMIN
	@Secured({ ROLE_ADMIN })
	@RequestMapping(value = UserConstant.URL_GET_ALL_USERS, method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUser(HttpServletRequest request) {
		List<User> userList = new ArrayList<User>();
		userList = userService.getAllUser();
		return userList;
	}

	// Display User Details to accounts which have role as USER
	@Secured({ ROLE_USER })
	@RequestMapping(value = UserConstant.URL_GET_USER, method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	// Admin has access to delete a user
	@Secured({ ROLE_ADMIN })
	@RequestMapping(value = UserConstant.URL_DELETE_USER, method = RequestMethod.DELETE)
	public Optional<User> deleteUser(@PathVariable Integer id) {
		Optional<User> user = userService.getUser(id);
		userService.deleteUser(id);
		return user;

	}

	// Display User Details to accounts which have role as USER
	@Secured({ ROLE_ADMIN })
	@RequestMapping(value = UserConstant.URL_ALL_PAYMENT, method = RequestMethod.GET)
	public List<Payment> getAllPayment(HttpServletRequest request) {
		try {
			List<Payment> paymentList = new ArrayList<Payment>();
			paymentList = userService.getAllPayment(request);
			return paymentList;
		} catch (UserException e) {
			// TODO: handle exception
			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
	}

}
