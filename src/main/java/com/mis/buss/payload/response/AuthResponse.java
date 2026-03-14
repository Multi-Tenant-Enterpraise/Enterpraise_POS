package com.mis.buss.payload.response;

import com.mis.buss.payload.dto.UserDto;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String jwt;
	private String message;
	private UserDto user;

}
