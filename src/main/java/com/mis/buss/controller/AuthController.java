package com.mis.buss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.payload.dto.UserDto;
import com.mis.buss.payload.response.AuthResponse;
import com.mis.buss.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	public final AuthService authService;
	
	//  http://localhost:4000/auth/signup
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>signupHandler(
		@RequestBody UserDto userDto
	) throws UserException{
		return ResponseEntity.ok(
				authService.signup(userDto)
		);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse>loginHandler(
		@RequestBody UserDto userDto
	) throws UserException{
		return ResponseEntity.ok(
				authService.login(userDto)
		);
	}
}
