/**
 * 
 */
package com.dell.OktaPayment.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.dell.OktaPayment.constant.PaymentConstant;
import com.dell.OktaPayment.dto.PaymentDataDto;
import com.dell.OktaPayment.entity.Payment;
import com.dell.OktaPayment.exception.PaymentException;
import com.dell.OktaPayment.repository.PaymentRepository;
import com.dell.OktaPayment.service.PaymentService;
import com.okta.spring.oauth.discovery.OidcDiscoveryClient;


/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PaymentRepository paymentRepository;

	@RequestMapping(value = PaymentConstant.URL_NEW_PAYMENT, method = RequestMethod.POST)
	public String newPayment(@RequestBody PaymentDataDto paymentDataDto, HttpServletRequest request) {
		try {
			Payment payment = modelMapper.map(paymentDataDto, Payment.class);
			paymentService.createNewPayment(payment);
			return PaymentConstant.SUCCESFULL_PAYMENT;
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
	}
	
	// get the list of all payments
	@RequestMapping(value = PaymentConstant.URL_ALL_PAYMENTS, method = RequestMethod.GET)
	public List<Payment> getAllPayment() {
		List<Payment> paymentList = new ArrayList<Payment>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			//UserDetails = Principal
		    String currentUserName = authentication.getName();
		    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();	
		    List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
		    listAuthorities.addAll(authorities);
		    if(listAuthorities.size()>1 && listAuthorities.get(1).getAuthority().equalsIgnoreCase("Admin"))
		    {
				paymentList = paymentService.getAllPayments();
				return paymentList;
		    }
		    else {
		    	paymentList = paymentService.getPaymentByUserId(currentUserName);
		    	return paymentList;
		    }
		    }
		else
		{
			throw new PaymentException("Not FOund", HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = PaymentConstant.URL_DELETE_PAYMENT, method = RequestMethod.DELETE)
	public Optional<Payment> deletePayment(@PathVariable Integer id, HttpServletRequest request) {
		Optional<Payment> payment = paymentRepository.findById(id);
		paymentService.deletePayment(id);
		return payment;
	}
	
	

}
