package com.dell.payment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dell.payment.entity.Payment;

public interface PaymentService {

	public void validateAdminUrl(HttpServletRequest httpServletRequest);

	public void validateUserUrl(HttpServletRequest httpServletRequest);

	public void createNewPayment(Payment payment);

	public void deletePayment(int id);

	public List<Payment> getPaymentByUserId(int id);

	public List<Payment> getAllPayments();

}
