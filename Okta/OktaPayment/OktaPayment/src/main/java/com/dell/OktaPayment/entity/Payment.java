/**
 * 
 */
package com.dell.OktaPayment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@Entity(name = "Payment")
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionid;

	@NotNull
	private float amount;

	@NotNull
	private String method;

	private Date date;

	private String userid;
	
	private String emailid;

	public int getTransactionId() {
		return transactionid;
	}

	public void setTransactionId(int transactionId) {
		this.transactionid = transactionId;
	}

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

	public Date getTime() {
		return date;
	}

	public void setTime(Date date) {
		this.date = date;
	}

	public String getUserId() {
		return userid;
	}

	public void setUserId(String userId) {
		this.userid = userId;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
