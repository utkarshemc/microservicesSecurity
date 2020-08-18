/**
 * 
 */
package com.dell.OktaPayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.OktaPayment.entity.Payment;



/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findByUserid(int id);
	List<Payment> findByEmailid(String id);
	boolean existsByUserid(int id);
}
