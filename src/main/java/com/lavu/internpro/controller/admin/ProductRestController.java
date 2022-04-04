package com.lavu.internpro.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lavu.internpro.dto.MessageResponse;
import com.lavu.internpro.dto.ProductResponse;
import com.lavu.internpro.dto.ProductForm;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.service.ProductService;
import com.lavu.internpro.utils.AppConstants;

@RestController
@RequestMapping("/api/admin/products")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseObject<ProductResponse> getAllPage(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return productService.getAllProductsPage(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getOne(@PathVariable(name = "id") Long id) {
		ProductResponse responseDto = productService.getProductById(id);
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@Valid @RequestPart ProductForm dto, @RequestPart MultipartFile file) {
		ProductResponse responseDto = productService.createProduct(dto,file);
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Thêm mới thành công "+responseDto.getName()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> update(@Valid @RequestPart ProductForm dto, @PathVariable(name = "id") Long id, @RequestPart MultipartFile file) {
		ProductResponse responseDto = productService.updateProduct(dto, id,file);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		productService.deleteProductById(id);
		return ResponseEntity.ok().body(new MessageResponse("Xóa thành công!"));
	}
}
