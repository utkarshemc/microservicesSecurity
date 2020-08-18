/**
 * 
 */
package com.dell.OktaPayment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dell.OktaPayment.constant.PaymentConstant;
import com.dell.OktaPayment.entity.Payment;
import com.dell.OktaPayment.repository.PaymentRepository;
import com.dell.payment.exception.PaymentException;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	
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
	public List<Payment> getPaymentByUserId(String id) {
		List<Payment> payments = new ArrayList<Payment>();
			payments = paymentRepository.findByEmailid(id);
			return payments;
			
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

}
