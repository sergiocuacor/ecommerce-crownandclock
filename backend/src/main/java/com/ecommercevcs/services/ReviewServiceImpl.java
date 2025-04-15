package com.ecommercevcs.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercevcs.controllers.ReviewController;
import com.ecommercevcs.dtos.ReviewDTO;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.entities.ReviewEntity;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.repositories.OrderRepository;
import com.ecommercevcs.repositories.ProductRepository;
import com.ecommercevcs.repositories.ReviewRepository;
import com.ecommercevcs.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewServiceImpl implements IReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ReviewDTO> getReviewsByProductId(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewDTO> getReviewsByUserId(Long userId) {
		
		return this.reviewRepository.findByUserId(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ReviewDTO createReview(ReviewDTO review, Long userId) throws Exception {
		ProductEntity product = this.productRepository.findById(review.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("this product not found"));
		UserEntity user = this.userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("this product not found"));
		System.out.println("Comprobando si el usuario "+  user.getName() +" ha comprado el producto..." + product.getName() );
		boolean hasPurchased = this.orderRepository.existsByUserIdAndProductId(userId, review.getProductId());
		System.out.println("Producto comprado " + hasPurchased);

		if (!hasPurchased) {
			throw new Exception("The user hasn't purchased this product");
		}
		if (this.reviewRepository.existsByProductAndUser(product, user)) {
			throw new Exception("The user has already reviewed this product");
		}

		ReviewEntity reviewEntity = new ReviewEntity();
		reviewEntity.setProduct(product);
		reviewEntity.setUser(user);
		reviewEntity.setRating(review.getRating());
		reviewEntity.setComment(review.getComment());

		product.addReview(reviewEntity);

		ReviewEntity savedReview = this.reviewRepository.save(reviewEntity);
		System.out.println("REVIEW REALIZADA " + savedReview.toString());
		
		this.productRepository.save(product);
		
		System.out.println("Product con nueva Review " + product.getReviews().getLast().toString());
		
		
		return mapToDTO(savedReview);
	}

	@Override
	public ReviewDTO updateReview(Long reviewId, ReviewDTO review, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewDTO removeReview(Long reviewId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReviewDTO mapToDTO(ReviewEntity review) {

		ReviewDTO reviewDto = new ReviewDTO();
		reviewDto.setId(review.getId());
		reviewDto.setProductId(review.getProduct().getId());
		reviewDto.setUserId(review.getUser().getId());
		reviewDto.setUsername(review.getUser().getName());
		reviewDto.setRating(review.getRating());
		reviewDto.setComment(review.getComment());
		reviewDto.setCreatedAt(review.getCreatedAt());

		return reviewDto;

	}
}
