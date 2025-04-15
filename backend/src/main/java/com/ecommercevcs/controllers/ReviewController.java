package com.ecommercevcs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.CreateReviewRequestDTO;
import com.ecommercevcs.dtos.ReviewDTO;
import com.ecommercevcs.services.IReviewService;
import com.ecommercevcs.services.ReviewServiceImpl;



@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	IReviewService reviewService;
	
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

}
