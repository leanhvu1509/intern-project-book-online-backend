package com.lavu.internpro.service;

import com.lavu.internpro.dto.JwtResponse;
import com.lavu.internpro.dto.LoginRequest;
import com.lavu.internpro.dto.SignUpRequest;

public interface AuthAdminService {

	JwtResponse signinUser(LoginRequest loginRequest);

	void registerUser(SignUpRequest request);

}
