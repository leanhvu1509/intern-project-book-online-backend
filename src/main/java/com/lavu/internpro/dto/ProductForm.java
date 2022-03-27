package com.lavu.internpro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lavu.internpro.enums.ProductStatus;

public class ProductForm extends BaseDto<String> {

	private Long id;

	@NotEmpty(message = "Không được để trống tên sách!")
	@Size(min = 3, message = "Tên sách không được nhỏ hơn 3 ký tự")
	private String name;

	@NotEmpty(message = "Không được để trống tên NXB!")
	@Size(min = 3, message = "Tên NXB không được nhỏ hơn 3 ký tự")
	private String publisher;

	@NotEmpty(message = "Không được để trống tên tác giả!")
	@Size(min = 3, message = "Tên tác giả không được nhỏ hơn 3 ký tự")
	private String author;

	@NotNull(message = "Không được để trống!")
	private int publishYear;

	@NotEmpty(message = "Không được để trống!")
	@Size(min = 3, message = "Không được nhỏ hơn 3 ký tự")
	private String description;

	@NotNull(message = "Không được để trống")
	private int price;

	private double discount;

	private String image;

	// MultiFile
//	private MultipartFile imageFile;

	private ProductStatus status;

	private Long categoryId;

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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProductForm() {
		super();
	}

}
