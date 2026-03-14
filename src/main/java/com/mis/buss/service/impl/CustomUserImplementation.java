package com.mis.buss.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mis.buss.model.User;
import com.mis.buss.repository.UserRepository;

@Service
public class CustomUserImplementation implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
			}
		
		GrantedAuthority authority = new SimpleGrantedAuthority(
				user.getRole().toString()
		); 
		
		Collection<GrantedAuthority> authorities = 
				Collections.singletonList(authority); 
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),user.getPassword(), authorities		);
		
	}

}
