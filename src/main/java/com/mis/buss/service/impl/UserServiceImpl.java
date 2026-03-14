package com.mis.buss.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mis.buss.configuration.JwtProvider;
import com.mis.buss.exceptions.UserException;
import com.mis.buss.model.User;
import com.mis.buss.repository.UserRepository;
import com.mis.buss.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	@Override
	public User getUserFromJwtToken(String token) throws UserException {
		// TODO Auto-generated method stub
		String email = jwtProvider.getEmailFromToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("Invalid token");
		}
		return user;
	}

	@Override
	public User getCurrentUser() throws UserException {
		// TODO Auto-generated method stub
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<User>getAllUsers(){
		return userRepository.findAll();
	}

}
