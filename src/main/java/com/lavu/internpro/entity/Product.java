package com.lavu.internpro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lavu.internpro.enums.ProductStatus;
@Entity
@Table(name = "products")
public class Product extends Base<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",columnDefinition = "nvarchar(100) not null",unique = true)
	private String name;
	
	@Column(name = "publisher",columnDefinition = "nvarchar(50) not null")
	private String publisher;
	
	@Column(name = "author",columnDefinition = "nvarchar(50) not null")
	private String author;
	
	@Column(name = "publish_year")
	private int publishYear;
	
	@Column(name = "description",columnDefinition = "longtext not null")
	private String description;
	
	@Column(name = "price",nullable = false)
	private int price;
	
	@Column(name = "discount")
	private double discount;

	@Column(name = "image")
	private String image;

	@Column(name = "status")
	private ProductStatus status;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;
	
	public Product(Long id, String name, String publisher, String author, int publishYear, String description,
			int price, double discount, String image, Category category,ProductStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.author = author;
		this.publishYear = publishYear;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.image = image;
		this.category = category;
		this.status = status;
	}

	public Product() {
		super();
	}

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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}	
	
	
	
}
