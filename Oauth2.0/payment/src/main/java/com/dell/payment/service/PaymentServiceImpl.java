/**
 * 
 */
package com.dell.payment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dell.payment.constant.PaymentConstant;
import com.dell.payment.entity.Payment;
import com.dell.payment.exception.PaymentException;
import com.dell.payment.repository.PaymentRepository;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void createNewPayment(Payment payment) {
		try {
			Date date = new Date();
			payment.setTime(new java.sql.Timestamp(date.getTime()));
			paymentRepository.save(payment);
		} catch (Exception e) {

			throw new PaymentException(PaymentConstant.ERROR_PAYMENT_FAILED, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public void deletePayment(int id) {
		if (paymentRepository.existsById(id)) {
			paymentRepository.deleteById(id);
		} else {
			throw new PaymentException(PaymentConstant.ERROR_INVALID_PAYMENTID, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Payment> getPaymentByUserId(int id) {
		List<Payment> payments = new ArrayList<Payment>();
		if (paymentRepository.existsByUserid(id)) {
			payments = paymentRepository.findByUserid(id);
			return payments;
		} else {
			throw new PaymentException(PaymentConstant.ERROR_INVALID_USERID, HttpStatus.NOT_FOUND);
		}
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

}
