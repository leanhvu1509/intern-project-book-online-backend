package com.lavu.internpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lavu.internpro.repository.RoleRepository;
import com.lavu.internpro.repository.UserRepository;
import com.lavu.internpro.service.UserService;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	 
}
