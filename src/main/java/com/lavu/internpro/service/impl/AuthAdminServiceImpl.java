package com.lavu.internpro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lavu.internpro.dto.JwtResponse;
import com.lavu.internpro.dto.LoginRequest;
import com.lavu.internpro.dto.SignUpRequest;
import com.lavu.internpro.entity.Role;
import com.lavu.internpro.entity.User;
import com.lavu.internpro.enums.RoleType;
import com.lavu.internpro.exception.AlreadyExistsException;
import com.lavu.internpro.repository.RoleRepository;
import com.lavu.internpro.repository.UserRepository;
import com.lavu.internpro.service.AuthAdminService;
import com.lavu.internpro.utils.JwtUtils;

@Service
public class AuthAdminServiceImpl implements AuthAdminService{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public void registerUser(SignUpRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new AlreadyExistsException("Username");
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyExistsException("Email");
		}
		User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
		Set<String> strRoles = request.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
	}

	
	@Override
	public JwtResponse signinUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}
}
