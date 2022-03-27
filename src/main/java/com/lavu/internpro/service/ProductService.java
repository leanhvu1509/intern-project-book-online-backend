package com.lavu.internpro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lavu.internpro.dto.ProductDto;
import com.lavu.internpro.dto.ProductForm;
import com.lavu.internpro.dto.ResponseObject;

public interface ProductService {

	void deleteProductById(Long id);

	ProductDto updateProduct(ProductForm dto, Long id,MultipartFile file);

	ProductDto createProduct(ProductForm dto,MultipartFile file);

	ProductDto getProductById(Long id);

	ResponseObject<ProductDto> getAllProductsPage(int pageNo, int pageSize, String sortBy, String sortDir);

	List<ProductDto> getAllProducts();

}
