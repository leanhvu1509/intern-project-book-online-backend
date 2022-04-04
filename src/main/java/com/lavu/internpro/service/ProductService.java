package com.lavu.internpro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lavu.internpro.dto.ProductResponse;
import com.lavu.internpro.dto.ProductForm;
import com.lavu.internpro.dto.ResponseObject;

public interface ProductService {

	void deleteProductById(Long id);

	ProductResponse updateProduct(ProductForm dto, Long id,MultipartFile file);

	ProductResponse createProduct(ProductForm dto,MultipartFile file);

	ProductResponse getProductById(Long id);

	ResponseObject<ProductResponse> getAllProductsPage(int pageNo, int pageSize, String sortBy, String sortDir);

	List<ProductResponse> getAllProducts();

}
