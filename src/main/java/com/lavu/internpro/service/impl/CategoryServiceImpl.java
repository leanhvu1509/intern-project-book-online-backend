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

import com.lavu.internpro.dto.CategoryDto;
import com.lavu.internpro.dto.ResponseObject;
import com.lavu.internpro.entity.Category;
import com.lavu.internpro.exception.AlreadyExistsException;
import com.lavu.internpro.exception.ResourceNoFoundException;
import com.lavu.internpro.repository.CategoryRepository;
import com.lavu.internpro.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryRepository.findAll().stream().map(cate -> mapper.map(cate, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseObject<CategoryDto> getAllCatogoriesPages(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Category> categories = categoryRepository.findAll(pageable);
		List<CategoryDto> content = categories.getContent().stream().map(cate -> mapper.map(cate, CategoryDto.class))
				.collect(Collectors.toList());
		ResponseObject<CategoryDto> response = new ResponseObject<CategoryDto>();
		response.setContent(content);
		response.setPageNo(categories.getNumber());
		response.setPageSize(categories.getSize());
		response.setTotalElements(categories.getTotalElements());
		response.setTotalPages(categories.getTotalPages());
		response.setLast(categories.isLast());
		return response;
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		Category entity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Category","id",id));
		CategoryDto dtoResponse = mapper.map(entity,CategoryDto.class);
		return dtoResponse;
	}

	@Override
	public CategoryDto createCategory(CategoryDto dtoRequest) {
		List<Category> found = categoryRepository.findByName(dtoRequest.getName().trim());
		if(found.size() > 0) {
			throw new AlreadyExistsException("Thể loại");
		}
		Category entity = mapper.map(dtoRequest, Category.class);
		Category category = categoryRepository.save(entity);
		CategoryDto dtoResponse = mapper.map(category, CategoryDto.class);
		return dtoResponse;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dtoRequest, Long id) {
		Category entity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Category","Id",id));
		if(entity.getName()==dtoRequest.getName().trim()) {
			throw new AlreadyExistsException("Thể loại");
		}
		entity.setName(dtoRequest.getName());
		entity.setStatus(dtoRequest.getStatus());
		Category updateCategory = categoryRepository.save(entity);
		CategoryDto dtoResponse = mapper.map(updateCategory, CategoryDto.class);
		return dtoResponse;
	}

	@Override
	public void deleteCategoryById(Long id) {
		Category entity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNoFoundException("Category","id",id));
		categoryRepository.delete(entity);
	}

}
