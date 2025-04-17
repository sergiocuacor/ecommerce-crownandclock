package com.ecommercevcs.entities;

import java.util.ArrayList;
import java.util.List;

import com.ecommercevcs.validation.IsPositiveValueDouble;
import com.ecommercevcs.validation.IsPositiveValueInteger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	@Column(unique = true)
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
	
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = true)
	@JsonBackReference(value = "product-brand")
	private BrandEntity brand;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "product-orderdetails")
	private List<OrderDetailsEntity> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "product-review")
	private List<ReviewEntity> reviews = new ArrayList<ReviewEntity>();
	

	@Column(name = "average_rating")
	private Double averageRating = 0.0;
	

	@Column(name = "rating_count")
	private Integer ratingCount = 0;
	
	
	public void calculateAverageRating() {
	    if (reviews == null || reviews.isEmpty()) {
	        this.averageRating = 0.0;
	        this.ratingCount = 0;
	    } else {
	        this.ratingCount = reviews.size();
	        // Filtrar reviews con rating no nulo antes de calcular el promedio
	        this.averageRating = reviews.stream()
	            .filter(review -> review.getRating() != null)  // Filtrar ratings nulos
	            .mapToDouble(ReviewEntity::getRating)
	            .average()
	            .orElse(0.0);
	    }
	}
	
	public void addReview(ReviewEntity review) {
		this.reviews.add(review);
		review.setProduct(this);
		this.calculateAverageRating();
	}
	
	public void removeReview(ReviewEntity review) {
		this.reviews.remove(review);
		review.setProduct(null);
		this.calculateAverageRating();
	}
	
	
	
	
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
	
	public String getnameBrand() {
		return "" + this.brand.getName();
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

	public List<ReviewEntity> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewEntity> reviews) {
		this.reviews = reviews;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	
	
	

}
