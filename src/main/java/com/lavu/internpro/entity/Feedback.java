package com.lavu.internpro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "fullname",columnDefinition = "nvarchar(100) not null")
	private String fullname;
	
	@Column(name = "email",columnDefinition = "varchar(100) not null")
	private String email;
	
	@Column(name = "phone",columnDefinition = "varchar(20) not null")
	private String phone;
	
	@Column(name = "subject",columnDefinition = "nvarchar(100) not null")
	private String subject;
	
	@Column(name = "content",columnDefinition = "text not null")
	private String content;

	@Column(name = "status")
	private short status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	
}
