package com.lavu.internpro.service;


public interface EmailService {

	void sendSimpleEmail(String toAddress, String subject, String message);

}
