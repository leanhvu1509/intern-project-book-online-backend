package com.lavu.internpro.service;

import com.lavu.internpro.dto.JwtResponse;
import com.lavu.internpro.dto.LoginAdminRequest;
import com.lavu.internpro.dto.UserForm;

public interface AuthAdminService {

	JwtResponse signinUser(LoginAdminRequest loginRequest);

	void registerUser(UserForm request);

}
