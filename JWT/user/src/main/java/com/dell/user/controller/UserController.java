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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dell.user.constants.UserConstant;
import com.dell.user.dto.AuthenticateRequest;
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
@CrossOrigin()
public class UserController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	// Any User can signUp into the application irrespective of roles.
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

	// Registered Users can login into the application
	@RequestMapping(value = UserConstant.URL_LOGIN, method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticateRequest authenticateRequest) {
		String jwt = "";
		try {
			jwt = userService.login(authenticateRequest);
			return ResponseEntity.ok(jwt);
		} catch (UserException e) {
			return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
		}
	}

	// Display list of Users to accounts which have role as ADMIN
	@RequestMapping(value = UserConstant.URL_GET_ALL_USERS, method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUser(HttpServletRequest request) {
		List<User> userList = new ArrayList<User>();

		try {
			userService.validateAdminUrl(request);
		} catch (UserException e) {

			throw new UserException(e.getMessage(), e.getHttpStatus());
		}

		userList = userService.getAllUser();
		return userList;
	}

	// Display User Details to accounts which have role as USER
	@RequestMapping(value = UserConstant.URL_GET_USER, method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable Integer id, HttpServletRequest request) {
		try {
			userService.validateUserUrl(request);
		} catch (UserException e) {

			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
		return userService.getUser(id);
	}

	// Admin has access to delete a user
	@RequestMapping(value = UserConstant.URL_DELETE_USER, method = RequestMethod.DELETE)
	public Optional<User> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
		try {
			userService.validateAdminUrl(request);
		} catch (UserException e) {

			throw new UserException(e.getMessage(), e.getHttpStatus());
		}

		Optional<User> user = userService.getUser(id);
		userService.deleteUser(id);
		return user;

	}

	// Get user Details by JWT
	@RequestMapping(value = UserConstant.URL_GET_USER_BY_JWT, method = RequestMethod.GET)
	public String getUserByJwt(HttpServletRequest request) {
		try {
			User user = userService.getUserDetailsByJwt(request);
			return user.getRoles();
		} catch (UserException e) {

			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
	}

	/*
	 * Get payment List by User. ADMIN will get all payment list and USER will get
	 * only theirs payment list
	 */
	@RequestMapping(value = UserConstant.URL_ALL_PAYMENT, method = RequestMethod.GET)
	public List<Payment> getAllPayment(HttpServletRequest request) {
		try {
			List<Payment> paymentList = new ArrayList<Payment>();
			User user = userService.getUserDetailsByJwt(request);
			if (user.getRoles().contains("ADMIN")) {
				paymentList = userService.getAllPayment(request);
			} else if (user.getRoles().contains("USER")) {
				paymentList = userService.getAllPaymentByUser(request, user.getId());
			}
			return paymentList;
		} catch (UserException e) {

			throw new UserException(e.getMessage(), e.getHttpStatus());
		}
	}

}
