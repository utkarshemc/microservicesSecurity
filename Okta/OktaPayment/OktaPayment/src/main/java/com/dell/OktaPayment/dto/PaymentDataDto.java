/**
 * 
 */
package com.dell.OktaPayment.dto;

import java.sql.Date;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
public class PaymentDataDto {
	private float amount;

	private String method;

	private String userid;
	
	private String emailid;

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	

}
