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
@Table(name = "contacts")
public class Contact {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Contact() {
		super();
	}

	public Contact(Long id, String fullname, String email, String phone, String subject, String content, short status,
			Date createdAt) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.createdAt = createdAt;
	}

	
	
}
