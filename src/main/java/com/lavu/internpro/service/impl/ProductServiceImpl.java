package com.lavu.internpro.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lavu.internpro.dto.ProductResponse;
import com.lavu.internpro.dto.ProductForm;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.entity.Category;
import com.lavu.internpro.entity.Product;
import com.lavu.internpro.exception.AlreadyExistsException;
import com.lavu.internpro.exception.ResourceNoFoundException;
import com.lavu.internpro.repository.CategoryRepository;
import com.lavu.internpro.repository.ProductRepository;
import com.lavu.internpro.service.ProductService;
import com.lavu.internpro.service.StorageService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private StorageService storageService;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream().map(p -> mapper.map(p, ProductResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseObject<ProductResponse> getAllProductsPage(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> products = productRepository.findAll(pageable);
		List<ProductResponse> content = products.getContent().stream().map(p -> mapper.map(p, ProductResponse.class))
				.collect(Collectors.toList());
		ResponseObject<ProductResponse> response = new ResponseObject<ProductResponse>();
		response.setContent(content);
		response.setPageNo(products.getNumber());
		response.setPageSize(products.getSize());
		response.setTotalElements(products.getTotalElements());
		response.setTotalPages(products.getTotalPages());
		response.setLast(products.isLast());
		return response;
	}

	@Override
	public ProductResponse getProductById(Long id) {
		Product found = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Sách", "Id", id));
		ProductResponse responseDto = mapper.map(found, ProductResponse.class);
		return responseDto;
	}

	@Override
	public ProductResponse createProduct(ProductForm dto,MultipartFile file) {
		List<Product> found = productRepository.findByName(dto.getName().trim());
		if (found.size() > 0) {
			throw new AlreadyExistsException("Tên sách");
		}
		Product entity = mapper.map(dto, Product.class);
		// category id
		Category category = categoryRepository.findById(dto.getCategoryId()).get();
		entity.setCategory(category);
		// image
		if (!file.isEmpty()) {
			entity.setImage(storageService.getStoredFileName(file));
			storageService.store(file, entity.getImage());
		}
		Product product = productRepository.save(entity);
		ProductResponse responseDto = mapper.map(product, ProductResponse.class);

		return responseDto;
	}

	@Override
	public ProductResponse updateProduct(ProductForm dto, Long id,MultipartFile file) {
		Product entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Sách", "Id", id));
		if (entity.getName() == dto.getName().trim()) {
			throw new AlreadyExistsException("Tên sách");
		}
		entity.setName(dto.getName());
		entity.setPublisher(dto.getPublisher());
		entity.setPublishYear(dto.getPublishYear());
		entity.setAuthor(dto.getAuthor());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setDescription(dto.getDescription());
		// categoryId
		Category category = categoryRepository.findById(dto.getCategoryId()).get();
		entity.setCategory(category);
		// image
		if (file.isEmpty()) {
			entity.setImage(entity.getImage());
		} else {
			entity.setImage(storageService.getStoredFileName(file));
			storageService.store(file, entity.getImage());
		}
		Product product = productRepository.save(entity);
		ProductResponse responseDto = mapper.map(product, ProductResponse.class);
		return responseDto;
	}

	@Override
	public void deleteProductById(Long id) {
		Product entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Sách", "Id", id));
		productRepository.delete(entity);
	}
}
