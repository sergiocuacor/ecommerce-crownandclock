package com.ecommercevcs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.CreateReviewRequestDTO;
import com.ecommercevcs.dtos.ReviewDTO;
import com.ecommercevcs.services.ReviewService;
import com.ecommercevcs.services.impl.ReviewServiceImpl;



@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/{userId}/{productId}")
	public ReviewDTO createReview(@RequestBody CreateReviewRequestDTO reviewRequest, @PathVariable Long userId, @PathVariable Long productId) throws Exception {
		
		logger.info("Recibida solicitud de review: User con ID: {}, Producto con ID: {}", userId, productId);
		logger.debug("Datos de la review: rating={}, comment={}", 
                reviewRequest.getRating(), reviewRequest.getComment());
		
		ReviewDTO review = new ReviewDTO();
		review.setComment(reviewRequest.getComment());
		review.setRating(reviewRequest.getRating());
		review.setProductId(productId);
		return this.reviewService.createReview(review, userId);
	}
	
	@GetMapping("/user/{userId}")
	public List<ReviewDTO> getReviewsByUserId(@PathVariable Long userId){
		return this.reviewService.getReviewsByUserId(userId);
	}
	
	@GetMapping("/product/{productId}")
	public List<ReviewDTO> getReviewsByProductId(@PathVariable Long productId) {
	    logger.info("Obteniendo reviews del producto con ID: {}", productId);
	    return this.reviewService.getReviewsByProductId(productId);
	}

	@PutMapping("/{reviewId}/user/{userId}")
	public ReviewDTO updateReview(
	        @PathVariable Long reviewId,
	        @RequestBody CreateReviewRequestDTO reviewRequest,
	        @PathVariable Long userId) throws Exception {
	    
	    logger.info("Actualizando review con ID: {} para usuario ID: {}", reviewId, userId);
	    logger.debug("Nuevos datos: rating={}, comment={}", 
	            reviewRequest.getRating(), reviewRequest.getComment());
	    
	    ReviewDTO review = new ReviewDTO();
	    review.setRating(reviewRequest.getRating());
	    review.setComment(reviewRequest.getComment());
	    
	    return this.reviewService.updateReview(reviewId, review, userId);
	}

	@DeleteMapping("/{reviewId}/user/{userId}")
	public ReviewDTO removeReview(
	        @PathVariable Long reviewId,
	        @PathVariable Long userId) throws Exception {
	    
	    logger.info("Eliminando review con ID: {} para usuario ID: {}", reviewId, userId);
	    return this.reviewService.removeReview(reviewId, userId);
	}
	
	
}
