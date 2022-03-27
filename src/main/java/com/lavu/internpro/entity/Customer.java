package com.lavu.internpro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fullname",columnDefinition = "nvarchar(100) not null")
	private String fullname;
	
	@Column(name = "username",unique = true,columnDefinition = "varchar(20) not null")
	private String username;
	
	@Column(name = "password",columnDefinition = "varchar(10) not null")
	private String password;

	@Column(name = "email",unique = true,columnDefinition = "varchar(100) not null")
	private String email;
	
	@Column(name = "phone",columnDefinition = "varchar(20) not null")
	private String phone;
	
	@Column(name = "address",columnDefinition = "nvarchar(255) not null")
	private String address;
	
	@Column(name = "about",columnDefinition = "nvarchar(255) not null")
	private String about;
	
	@Column(name = "status")
	private short status;
	
}
