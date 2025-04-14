package com.ecommercevcs.dtos;

import com.ecommercevcs.validation.IsPositiveValueInteger;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateReviewRequestDTO {
	
	

	public CreateReviewRequestDTO() {
		super();
	}
	
	

	public CreateReviewRequestDTO(@Max(5) Integer rating, @Size(max = 500) String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}



	@NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
	private Integer rating;
	
	@Size(max = 500)
	private String comment;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
