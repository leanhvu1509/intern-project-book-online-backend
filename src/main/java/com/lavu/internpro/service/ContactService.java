package com.lavu.internpro.service;

import java.util.List;

import com.lavu.internpro.dto.ContactDto;
import com.lavu.internpro.dto.ResponseObject;

public interface ContactService {

	void deleteContactById(Long id);

	ContactDto createContact(ContactDto dtoRequest);

	ContactDto getContactById(Long id);

	ResponseObject<ContactDto> getAllContactsPages(int pageNo, int pageSize, String sortBy, String sortDir);

	List<ContactDto> getAllContacts();

}
