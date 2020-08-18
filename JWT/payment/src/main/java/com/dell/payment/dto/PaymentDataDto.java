/**
 * 
 */
package com.dell.payment.dto;

import java.sql.Date;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
public class PaymentDataDto {
	private float amount;

	private String method;

	private int userid;

	public float getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
