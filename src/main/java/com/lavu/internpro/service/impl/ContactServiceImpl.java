package com.lavu.internpro.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.lavu.internpro.dto.ContactDto;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.entity.Contact;
import com.lavu.internpro.exception.ResourceNoFoundException;
import com.lavu.internpro.repository.ContactRepository;
import com.lavu.internpro.service.ContactService;
import com.lavu.internpro.service.EmailService;

public class ContactServiceImpl implements ContactService{

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public List<ContactDto> getAllContacts() {
		return contactRepository.findAll().stream().map(cate -> mapper.map(cate, ContactDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseObject<ContactDto> getAllContactsPages(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Contact> contacts = contactRepository.findAll(pageable);
		List<ContactDto> content = contacts.getContent().stream().map(c -> mapper.map(c, ContactDto.class))
				.collect(Collectors.toList());
		ResponseObject<ContactDto> response = new ResponseObject<ContactDto>();
		response.setContent(content);
		response.setPageNo(contacts.getNumber());
		response.setPageSize(contacts.getSize());
		response.setTotalElements(contacts.getTotalElements());
		response.setTotalPages(contacts.getTotalPages());
		response.setLast(contacts.isLast());
		return response;
	}

	@Override
	public ContactDto getContactById(Long id) {
		Contact entity = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Contact","id",id));
		ContactDto dtoResponse = mapper.map(entity,ContactDto.class);
		return dtoResponse;
	}

	@Override
	public ContactDto createContact(ContactDto dtoRequest) {
		emailService.sendSimpleEmail(dtoRequest.getEmail(), dtoRequest.getSubject(), dtoRequest.getContent());
		Contact entity = mapper.map(dtoRequest, Contact.class);
		entity.setCreatedAt(new Date());
		Contact Contact = contactRepository.save(entity);
		ContactDto dtoResponse = mapper.map(Contact, ContactDto.class);
		return dtoResponse;
	}

	@Override
	public void deleteContactById(Long id) {
		Contact entity = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Contact","id",id));
		contactRepository.delete(entity);
	}
}
