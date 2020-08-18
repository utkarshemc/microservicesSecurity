package com.dell.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String Username);

}
