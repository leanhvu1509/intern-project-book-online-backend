package com.lavu.internpro.controller.admin;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lavu.internpro.dto.CategoryDto;
import com.lavu.internpro.dto.MessageResponse;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.service.CategoryService;
import com.lavu.internpro.utils.AppConstants;

@RestController
@RequestMapping("/api/admin/categories")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseObject<CategoryDto> getAllPage(
			 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			){
		return categoryService.getAllCatogoriesPages(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getOne(@PathVariable(name = "id") Long id) {
		CategoryDto responseDto = categoryService.getCategoryById(id); 
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto dto) throws URISyntaxException {
		CategoryDto responseDto = categoryService.createCategory(dto); 
		return ResponseEntity.created(new URI("/api/admin/categories/"+responseDto.getId())).body(responseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto dto, @PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(categoryService.updateCategory(dto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		categoryService.deleteCategoryById(id);
		return ResponseEntity.ok().body(new MessageResponse("Xóa thành công!"));
	}
}
