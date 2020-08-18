/**
 * 
 */
package com.dell.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.user.entity.User;

/**
 * @author bhardu
 * @Since Apr 23, 2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

	User findByUsername(String username);

}
