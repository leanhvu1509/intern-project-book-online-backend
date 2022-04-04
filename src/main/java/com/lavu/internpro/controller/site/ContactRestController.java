package com.lavu.internpro.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavu.internpro.dto.ContactDto;
import com.lavu.internpro.service.ContactService;

@RestController
@RequestMapping("/api/site")
public class ContactRestController {

	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/contact")
	public ResponseEntity<?> sendContact(@RequestBody ContactDto dto){
		try {
			contactService.createContact(dto);
		} catch (MailException e) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
	}
	
}
