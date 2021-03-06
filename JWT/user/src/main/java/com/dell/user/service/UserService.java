package com.dell.user.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.dell.user.dto.AuthenticateRequest;
import com.dell.user.entity.Payment;
import com.dell.user.entity.User;
import com.dell.user.exception.UserException;

/**
 * @author bhardu
 * @Since Apr 23, 2020
 */

public interface UserService {

	public void signup(User user) throws UserException;

	public String login(AuthenticateRequest authenticateRequest);

	public List<User> getAllUser();

	public void validateAdminUrl(HttpServletRequest httpServletRequest);

	public void validateUserUrl(HttpServletRequest httpServletRequest);

	public Optional<User> getUser(Integer id);

	public void deleteUser(Integer id);

	public User getUserDetailsByJwt(HttpServletRequest httpServletRequest);

	public List<Payment> getAllPayment(HttpServletRequest httpServletRequest);

	public List<Payment> getAllPaymentByUser(HttpServletRequest httpServletRequest, Integer id);

}
