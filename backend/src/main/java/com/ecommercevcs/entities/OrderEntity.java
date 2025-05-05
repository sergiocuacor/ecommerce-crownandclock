package com.ecommercevcs.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.ecommercevcs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class OrderEntity {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    @NotNull
	    @JsonBackReference(value="user-order")
	    private UserEntity user;
	    private LocalDateTime orderDate;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "status", nullable = false)
	    private OrderStatus status;
	    
	    @Column(name = "total", nullable = false)
	    private Double total;
	    
	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference(value="order-orderdetails")
		private List<OrderDetailsEntity> orderDetails = new ArrayList<OrderDetailsEntity>();
	    
		@Column(name = "discount_applied")
		private String discountApplied;
		
		@Column(name = "discount_percentage")
		private Integer discountPercentage;
	    
	    @PrePersist
	    public void prePersist() {
	        orderDate = LocalDateTime.now();
	        if(status == null) {
	            status = OrderStatus.PENDING;
	        }
	    }

		public OrderEntity(Long id, UserEntity user, LocalDateTime orderDate,
				com.ecommercevcs.utils.enums.OrderStatus status, Double total) {
			super();
			this.id = id;
			this.user = user;
			this.orderDate = orderDate;
			this.status = status;
			this.total = total;
		}

		public OrderEntity() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public UserEntity getUser() {
			return user;
		}

		public void setUser(UserEntity user) {
			this.user = user;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}

		public List<OrderDetailsEntity> getOrderDetails() {
			return orderDetails;
		}

		public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
			this.orderDetails = orderDetails;
		}
		
		public void addOrderDetail(OrderDetailsEntity orderDetail) {
			this.orderDetails.add(orderDetail);
			orderDetail.setOrder(this);
		}
	
		public String getDiscountApplied() {
			return discountApplied;
		}

		public void setDiscountApplied(String discountApplied) {
			this.discountApplied = discountApplied;
		}

		public Integer getDiscountPercentage() {
			return discountPercentage;
		}

		public void setDiscountPercentage(Integer discountPercentage) {
			this.discountPercentage = discountPercentage;
		}
		
		public Long getUserId() {
			return this.user.getId();
		}
	    
}
