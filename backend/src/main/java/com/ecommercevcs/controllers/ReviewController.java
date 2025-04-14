package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.CreateReviewRequestDTO;
import com.ecommercevcs.dtos.ReviewDTO;
import com.ecommercevcs.services.ReviewService;



@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/{userId}/{productId}")
	public ReviewDTO createReview(@RequestBody CreateReviewRequestDTO reviewRequest, @PathVariable Long userId, @PathVariable Long productId) throws Exception {
		System.out.println(reviewRequest.getComment() + reviewRequest.getComment());
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
