package com.lavu.internpro.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavu.internpro.dto.LoginAdminRequest;
import com.lavu.internpro.dto.MessageResponse;
import com.lavu.internpro.dto.UserForm;
import com.lavu.internpro.service.AuthAdminService;

@CrossOrigin(maxAge = 360,origins = "*")
@RestController
@RequestMapping("/api/admin/auth")
public class AuthRestController {

	@Autowired
	private AuthAdminService authAdminService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserForm signUpRequest){
		authAdminService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MessageResponse("User register successfull!"));
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginAdminRequest loginRequest){
		return ResponseEntity.ok(authAdminService.signinUser(loginRequest));
	}
}
