package com.ecommercevcs.entities;

import java.util.List;

import com.ecommercevcs.validation.IsPositiveValueDouble;
import com.ecommercevcs.validation.IsPositiveValueInteger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@IsPositiveValueDouble
	@NotNull
	private Double price;

	@NotNull
	@IsPositiveValueInteger
	private Integer stock;
	
	@NotBlank
	private String mask;
	
	@NotBlank
	private String urlImage1;
	
	private String urlImage2;
	
	

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = true)
	@JsonBackReference(value = "product-brand")
	private BrandEntity brand;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "product-orderdetails")
	private List<OrderDetailsEntity> orderDetails;

	public void addStock(Integer quantity) {
		this.stock += quantity;
	}

	public void removeStock(Integer quantityOrdered) throws IllegalArgumentException {
		if (this.stock < quantityOrdered) {
			throw new IllegalArgumentException("Insufficient stock");
		}
		this.stock -= quantityOrdered;
	}

	public ProductEntity() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity category) {
		this.brand = category;
	}

	public String getIdBrand() {
		return "" + this.brand.getId();
	}

	public List<OrderDetailsEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void addOrderDetails(OrderDetailsEntity orderDetail) {
		this.orderDetails.add(orderDetail);
		orderDetail.setProduct(this);
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getUrlImage1() {
		return urlImage1;
	}

	public void setUrlImage1(String urlImage1) {
		this.urlImage1 = urlImage1;
	}

	public String getUrlImage2() {
		return urlImage2;
	}

	public void setUrlImage2(String urlImage2) {
		this.urlImage2 = urlImage2;
	}
	
	

}
