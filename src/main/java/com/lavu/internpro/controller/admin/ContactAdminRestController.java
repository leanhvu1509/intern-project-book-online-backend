package com.lavu.internpro.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavu.internpro.dto.ContactDto;
import com.lavu.internpro.dto.MessageResponse;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.service.ContactService;
import com.lavu.internpro.utils.AppConstants;

@Repository
@RequestMapping("api/admin/feedbacks")
@PreAuthorize("hasRole('ADMIN')")
public class ContactAdminRestController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public ResponseObject<ContactDto> getAllPage(
			 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			){
		return contactService.getAllContactsPages(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDto> getOne(@PathVariable(name = "id") Long id) {
		ContactDto responseDto = contactService.getContactById(id); 
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		contactService.deleteContactById(id);
		return ResponseEntity.ok().body(new MessageResponse("Xóa thành công!"));
	}
	
}
