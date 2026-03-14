package com.mis.buss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.buss.model.User;

public interface UserRepository extends JpaRepository<User, Long > {
	
	User findByEmail(String email);

}
