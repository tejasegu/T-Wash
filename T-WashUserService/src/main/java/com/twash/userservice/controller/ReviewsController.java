package com.twash.userservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twash.userservice.exception.ResourceNotFoundException;
import com.twash.userservice.model.Reviews;

import com.twash.userservice.repository.ReviewsRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ReviewsController {
	@Autowired
	private ReviewsRepository reviewsrepo;
	
	//To Insert Review Into DataBase
		@Operation(summary = "Adds a new review")
		@PostMapping("/review")
		public ResponseEntity<String> createReview(  @RequestBody Reviews review) {
			try {
				reviewsrepo.save(review);
				return ResponseEntity.ok("Review added suceesfully with Id:"+review.getId());
				}
				catch (Exception e) {
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
				}
		}
		
		 //To Get All Reviews Data From DataBase
		  @Operation(summary = "View a list of all reviews")
		  @GetMapping("/reviews") 
		  public List<Reviews> getAllReviews() { 
			  return reviewsrepo.findAll() ; 
		}
		  
		  //To Get All Reviews Data From DataBase Using Washer Id
		  @Operation(summary = "View a list of all reviews for a specific washer")
		  @GetMapping("/reviews/{washerid}") 
		  public ResponseEntity<List<Reviews>> getAllReviewsByWasherId(@PathVariable(value="washerid")String washerid)  throws ResourceNotFoundException{ 

			List<Reviews>reviewslist= reviewsrepo.findByWasherid(washerid).orElseThrow(()->new ResourceNotFoundException("Reviews Not Found For This id::"+washerid));
			  return ResponseEntity.ok(reviewslist); 	
		}
		
		  @Operation(summary = "View average rating for specific washer")
		  @GetMapping("/rating/{washerid}") 
		  public ResponseEntity<Double> getAverageRatingByWasherId(@PathVariable(value="washerid")String washerid) { 
			 try {
			  List<Reviews>reviewslist= reviewsrepo.findByWasherid(washerid).get();
			Double rating=reviewslist.stream().mapToDouble(o->o.getRating()).average().getAsDouble();
			  return ResponseEntity.ok(rating);
			 }
			 catch(Exception e) {
				 Double defaultrating= 3.0;
				 return ResponseEntity.ok(defaultrating) ;
			 }
			  
		  }
}
