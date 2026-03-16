package com.mis.buss.service;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.payload.dto.UserDto;
import com.mis.buss.payload.response.AuthResponse;



public interface AuthService {
	AuthResponse signup(UserDto userDto) throws UserException;
	AuthResponse login(UserDto userDto) throws UserException;
	
	

}
