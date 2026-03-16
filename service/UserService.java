package com.mis.buss.service;

import java.util.List;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.model.User;

public interface UserService {
	
	User getUserFromJwtToken(String token) throws UserException;
	User getCurrentUser() throws UserException;
	User getUserByEmail(String email) throws UserException, Exception;
	User getUserById(Long id);
	List<User> getAllUsers();

}
