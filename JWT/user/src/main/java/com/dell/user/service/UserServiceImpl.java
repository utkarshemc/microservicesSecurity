package com.dell.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dell.user.constants.UserConstant;
import com.dell.user.dto.AuthenticateRequest;
import com.dell.user.entity.Payment;
import com.dell.user.entity.User;
import com.dell.user.exception.UserException;
import com.dell.user.repository.UserRepository;

/**
 * @author bhardu
 * @Since Apr 23, 2020
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	@Value("${paymentService}")
	private String paymentService;

	@Value("${authService}")
	private String authService;

	@Override
	public void signup(User user) throws UserException {
		if (!userRepository.existsByUsername(user.getUsername())) {
			try {
				userRepository.save(user);
			} catch (Exception e) {
				throw new UserException(UserConstant.ERROR_SAVE_DATA, HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new UserException(UserConstant.ERROR_USERNAME_CONFLICT, HttpStatus.CONFLICT);
		}
	}

	@Override
	public String login(AuthenticateRequest authenticateRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<AuthenticateRequest> entity = new HttpEntity<AuthenticateRequest>(authenticateRequest, headers);
		String url = authService + "/authenticate";
		try {
			return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		} catch (Exception e) {
			throw new UserException(UserConstant.ERROR_LOGIN, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	// Get all user list
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		userList = userRepository.findAll();
		return userList;
	}

	@Override
	/*
	 * Validate from Auth Service weather the Role is ADMIN for the account which is
	 * calling this URL
	 */
	public void validateAdminUrl(HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (httpServletRequest.getHeader(UserConstant.AUTHORIZATION) != null) {
			headers.setBearerAuth(httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7));
		} else {
			throw new UserException(UserConstant.ERROR_JWT_TOKEN, HttpStatus.FORBIDDEN);
		}
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url = authService + "/validateAdminUrl";
		try {
			restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		} catch (Exception e) {
			throw new UserException(UserConstant.ERROR_AUTH, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public Optional<User> getUser(Integer id) {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id);
		} else {
			throw new UserException(UserConstant.ERROR_INVALID_USERID, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	/*
	 * Validate from AUTH Service weather the role is USER for the account which is
	 * calling this URL
	 */
	public void validateUserUrl(HttpServletRequest httpServletRequest) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (httpServletRequest.getHeader(UserConstant.AUTHORIZATION) != null) {
			headers.setBearerAuth(httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7));
		} else {
			throw new UserException(UserConstant.ERROR_JWT_TOKEN, HttpStatus.FORBIDDEN);
		}
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url = authService + "/validateUserUrl";
		try {
			restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		} catch (Exception e) {

			throw new UserException(UserConstant.ERROR_AUTH, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public void deleteUser(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new UserException(UserConstant.ERROR_INVALID_USERID, HttpStatus.NOT_FOUND);
		}
	}

	public User getUserDetailsByJwt(HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (httpServletRequest.getHeader(UserConstant.AUTHORIZATION) != null) {
			headers.setBearerAuth(httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7));
		} else {
			throw new UserException(UserConstant.ERROR_JWT_TOKEN, HttpStatus.FORBIDDEN);
		}
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String userName = "";
		String url = authService + "/getUserByJwt/"
				+ httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7);
		try {
			userName = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
			User user = userRepository.findByUsername(userName);
			return user;
		} catch (Exception e) {
			throw new UserException(UserConstant.ERROR_AUTH, HttpStatus.FORBIDDEN);
		}
	}

	public List<Payment> getAllPayment(HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (httpServletRequest.getHeader(UserConstant.AUTHORIZATION) != null) {
			headers.setBearerAuth(httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7));
		} else {
			throw new UserException(UserConstant.ERROR_JWT_TOKEN, HttpStatus.FORBIDDEN);
		}
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url = paymentService + "/allPayment";
		try {
			List<Payment> response = restTemplate
					.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Payment>>() {
					}).getBody();
			return response;
		} catch (Exception e) {
			throw new UserException(UserConstant.ERROR_AUTH, HttpStatus.FORBIDDEN);
		}
	}

	public List<Payment> getAllPaymentByUser(HttpServletRequest httpServletRequest, Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if (httpServletRequest.getHeader(UserConstant.AUTHORIZATION) != null) {
			headers.setBearerAuth(httpServletRequest.getHeader(UserConstant.AUTHORIZATION).substring(7));
		} else {
			throw new UserException(UserConstant.ERROR_JWT_TOKEN, HttpStatus.FORBIDDEN);
		}
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url = paymentService + "/getPaymentByUser/" + id;
		try {
			List<Payment> response = restTemplate
					.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Payment>>() {
					}).getBody();
			return response;
		} catch (Exception e) {
			throw new UserException(UserConstant.ERROR_AUTH, HttpStatus.FORBIDDEN);
		}
	}
}
