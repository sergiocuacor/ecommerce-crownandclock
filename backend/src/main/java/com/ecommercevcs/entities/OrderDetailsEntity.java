package com.ecommercevcs.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference
	private OrderEntity order;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	@JsonBackReference
	private ProductEntity product;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(name = "unit_price", nullable = false)
	private Double unitPrice;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	public void calculateSubtotal() {
		this.subtotal = this.unitPrice*this.quantity;
	}

	public Double getSubTotal() {
		return subtotal;
	}


	public void setSubTotal(Double subTotal) {
		this.subtotal = subTotal;
	}


	public OrderDetailsEntity() {
		super();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
	
}
