package com.lavu.internpro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lavu.internpro.enums.CategoryStatus;

public class CategoryDto extends BaseDto<String> {

	private Long id;

	@NotEmpty(message = "Không được để trống!")
	@Size(min = 3, message = "Tên thể loại phải dài từ 3-50 ký tự.")
	private String name;

	private CategoryStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryStatus getStatus() {
		return status;
	}

	public void setStatus(CategoryStatus status) {
		this.status = status;
	}

	

}
