/**
 * 
 */
package com.dell.payment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dell.payment.constant.PaymentConstant;
import com.dell.payment.dto.PaymentDataDto;
import com.dell.payment.entity.Payment;
import com.dell.payment.exception.PaymentException;
import com.dell.payment.repository.PaymentRepository;
import com.dell.payment.service.PaymentService;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@CrossOrigin()
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentRepository paymentRepository;

	// A new payment will be created by any account that has USER role
	@RequestMapping(value = PaymentConstant.URL_NEW_PAYMENT, method = RequestMethod.POST)
	public String newPayment(@RequestBody PaymentDataDto paymentDataDto, HttpServletRequest request) {
		try {
			paymentService.validateUserUrl(request);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
		try {
			Payment payment = modelMapper.map(paymentDataDto, Payment.class);
			paymentService.createNewPayment(payment);
			return PaymentConstant.SUCCESFULL_PAYMENT;
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
	}

	@RequestMapping(value = PaymentConstant.URL_ALL_PAYMENTS, method = RequestMethod.GET)
	public List<Payment> getAllPayment(HttpServletRequest request) {
		List<Payment> paymentList = new ArrayList<Payment>();
		try {
			paymentService.validateAdminUrl(request);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
		paymentList = paymentService.getAllPayments();
		if (!paymentList.isEmpty()) {
			return paymentList;
		} else {
			throw new PaymentException(PaymentConstant.ERROR_PAYMENT_EMPTY, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = PaymentConstant.URL_PAYMENT_LIST_BY_USER, method = RequestMethod.GET)
	public List<Payment> getPaymentByUser(@PathVariable Integer id, HttpServletRequest request) {
		try {
			paymentService.validateUserUrl(request);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
		List<Payment> paymentListByUser = new ArrayList<Payment>();
		paymentListByUser = paymentService.getPaymentByUserId(id);
		if (paymentListByUser != null) {
			return paymentListByUser;
		} else {
			throw new PaymentException(PaymentConstant.ERROR_PAYMENT_EMPTY, HttpStatus.NOT_FOUND);
		}
	}

	// Delete a payment with ADMIN role
	@RequestMapping(value = PaymentConstant.URL_DELETE_PAYMENT, method = RequestMethod.DELETE)
	public Optional<Payment> deletePayment(@PathVariable Integer id, HttpServletRequest request) {
		try {
			paymentService.validateAdminUrl(request);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
		Optional<Payment> payment = paymentRepository.findById(id);
		paymentService.deletePayment(id);
		return payment;
	}

	@RequestMapping(value = PaymentConstant.URL_PAYMENT_LIST, method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getPaymentList(HttpServletRequest request) {
		List<Payment> paymentList = new ArrayList<Payment>();
		try {
			paymentService.validateAdminUrl(request);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage(), e.getHttpStatus());
		}
		paymentList = paymentService.getAllPayments();
		if (!paymentList.isEmpty()) {
			return new ResponseEntity<>(paymentList, HttpStatus.OK);
		} else {
			throw new PaymentException(PaymentConstant.ERROR_PAYMENT_EMPTY, HttpStatus.NOT_FOUND);
		}
	}
}
