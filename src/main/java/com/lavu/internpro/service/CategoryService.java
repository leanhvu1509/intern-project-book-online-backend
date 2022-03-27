package com.lavu.internpro.service;

import java.util.List;

import com.lavu.internpro.dto.CategoryDto;
import com.lavu.internpro.dto.ResponseObject;

public interface CategoryService {

	void deleteCategoryById(Long id);

	CategoryDto updateCategory(CategoryDto dto, Long id);

	CategoryDto createCategory(CategoryDto dtoRequest);

	CategoryDto getCategoryById(Long id);

	ResponseObject<CategoryDto> getAllCatogoriesPages(int pageNo, int pageSize, String sortBy, String sortDir);

	List<CategoryDto> getAllCategories();

}
