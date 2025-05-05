package com.ecommercevcs.services;

import java.util.List;

import com.ecommercevcs.dtos.ReviewDTO;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.entities.UserEntity;

public interface ReviewService {
	
	public List<ReviewDTO> getReviewsByProductId(Long productId);
	
	public List<ReviewDTO> getReviewsByUserId(Long userId);
	
	public ReviewDTO createReview(ReviewDTO review, Long userId) throws Exception;
	
	public ReviewDTO updateReview(Long reviewId,ReviewDTO review, Long userId);
	
	public ReviewDTO removeReview(Long reviewId, Long userId);
	
	
}
