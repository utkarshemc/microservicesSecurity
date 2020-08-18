package com.dell.OktaPayment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dell.OktaPayment.entity.Payment;


public interface PaymentService {

	public void createNewPayment(Payment payment);

	public void deletePayment(int id);

	public List<Payment> getPaymentByUserId(String id);

	public List<Payment> getAllPayments();

}
