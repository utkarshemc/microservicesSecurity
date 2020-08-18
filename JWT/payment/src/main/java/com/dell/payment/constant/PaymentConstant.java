/**
 * 
 */
package com.dell.payment.constant;

/**
 * @author bhardu
 * @Since May 4, 2020
 */
public interface PaymentConstant {

	public final String AUTHORIZATION = "Authorization";
	public final String SUCCESFULL_PAYMENT = "Payment Details was succesfully Saved";
	public final String SUCCESFULL_DELETE = "Succesfully Deleted the User:-";

	public final String URL_NEW_PAYMENT = "/newPayment";
	public final String URL_ALL_PAYMENTS = "/allPayment";
	public final String URL_PAYMENT_LIST_BY_USER = "/getPaymentByUser/{id}";
	public final String URL_DELETE_PAYMENT = "/deletePayment/{id}";
	public final String URL_PAYMENT_LIST = "/paymentList";

	public final String ERROR_JWT_TOKEN = "Jwt token is not present";
	public final String ERROR_AUTH = "Incorrect Jwt or the auth level is not permissable";
	public final String ERROR_PAYMENT_FAILED = "Unable to complete the Payment";
	public final String ERROR_PAYMENT_EMPTY = "The payment list is empty";
	public final String ERROR_INVALID_USERID = "User with the given id does not exists";
	public final String ERROR_INVALID_PAYMENTID = "Payment with the given id does not exists";

}
