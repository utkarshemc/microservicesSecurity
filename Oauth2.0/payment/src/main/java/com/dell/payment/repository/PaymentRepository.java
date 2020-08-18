/**
 * 
 */
package com.dell.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.payment.entity.Payment;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findByUserid(int id);
	boolean existsByUserid(int id);
}
