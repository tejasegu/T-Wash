package com.twash.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.twash.apigateway.model.Bookings;
import com.twash.apigateway.model.Reviews;
import com.twash.apigateway.model.Users;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableHystrix

@RequestMapping("/admin")
public class AdminController {
	@Autowired
	
	RestTemplate restemplate;
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
	 
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate;
	}
	
	

	/********************All Urls Acessed by the admin**********************************/
	
	
	String GETUSER="http://t-washuserservice/users/find/";
	String GETALLUSERS="http://t-washuserservice/users/get";
	String DELUSER="http://t-washuserservice/users/delete/";
	String ALLBOOK="http://t-washbookingservice/bookings";
	String BOOKINGBYID="http://t-washbookingservice/bookings/find/";
	String DELBOOKING="http://t-washbookingservice/bookings/delete/";
	String ALLREV="http://t-washuserservice/reviews";
	String ALLREVBYWASHER="http://t-washuserservice/reviews/";
	String RATINGBYWASHER="http://t-washuserservice/rating/";
	String INCOME="http://t-washbookingservice/bookings/income";
	
	
   /*********************************************************************************************/
	
	
	
	
	/*************************Admin Access Methods**********************************************/
	
	
	/*Get User By Id*/
	@HystrixCommand(fallbackMethod = "getUserFallback")
	@GetMapping("/find/{id}")
	
	public ResponseEntity<?> getuser( @PathVariable("id") String id) {
		
	try {
	  Users use=restemplate.getForObject(GETUSER+id, Users.class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	}
	
	
	
	/*Get All Users*/
	@HystrixCommand(fallbackMethod = "getallUserFallback")
	@GetMapping("/get")
	
	public ResponseEntity<?> getAllUsers() {
	try {
	  Users[] use=restemplate.getForObject(GETALLUSERS, Users[].class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	/*Delete User*/
	@HystrixCommand(fallbackMethod = "deleteUserFallback")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser( @PathVariable("id") String id) {
	try {
	   restemplate.delete(DELUSER+id);
		return ResponseEntity.ok("User deleted sucessfully") ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	
	/*Get All Bookings*/
	@HystrixCommand(fallbackMethod = "allBookingsFallback")
	@GetMapping("/bookings")
	public ResponseEntity<?> getAllBookings() {
	try {
	  Bookings[] book=restemplate.getForObject(ALLBOOK, Bookings[].class);
		return ResponseEntity.ok(book) ;
        }
	catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	}
	
      
	
	/*Get Booking By Id*/
	  @HystrixCommand(fallbackMethod = "getBookingFallback")
	  @GetMapping("/bookings/find/{id}")
	  public ResponseEntity<?> getBookingById(@PathVariable("id") String id) { 
	try { 
		Bookings book=restemplate.getForObject(BOOKINGBYID+id, Bookings.class); return
	  ResponseEntity.ok(book) ; 
		}
	  catch(Exception e)
	  {
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); } }
	  

	  
	/*Delete Booking*/
	@HystrixCommand(fallbackMethod = "deleteBookingFallback")
	@DeleteMapping("/bookings/delete/{id}")
	public ResponseEntity<?> deleteBooking( @PathVariable("id") String id) {
	try {
	   restemplate.delete(DELBOOKING+id);
		return ResponseEntity.ok("Booking deleted sucessfully") ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	/*Get All Reviews*/
	@HystrixCommand(fallbackMethod = "allReviewsFallback")
	@GetMapping("/reviews")
	public ResponseEntity<?> getAllReviews() {
	try {
	 Reviews[] use=restemplate.getForObject(ALLREV, Reviews[].class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	
	/*Get Review By Id*/
	@HystrixCommand(fallbackMethod = "reviewFallback")
	@GetMapping("/reviews/{id}")
	public ResponseEntity<?> getAllReviewsByWasherId(@PathVariable("id") String id) {
	try {
	 Reviews[] use=restemplate.getForObject(ALLREVBYWASHER+id, Reviews[].class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}

    
	/*Get Rating By Id*/
	@HystrixCommand(fallbackMethod = "ratingFallback")
	@GetMapping("/rating/{id}")
	public ResponseEntity<?> getRatingByWasherId(@PathVariable("id") String id) {
	try {
	Double use=restemplate.getForObject(RATINGBYWASHER+id, Double.class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	
	/*Get Total Income*/
	@HystrixCommand(fallbackMethod = "incomeFallback")
	@GetMapping("/income")
	public ResponseEntity<?> getIncome() {
	try {
	Double use=restemplate.getForObject(INCOME, Double.class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	}
	}
	
	
	/***********************************************************************************************************/
	
	/***********************************FallBack Methods********************************************************/
	
	
	public ResponseEntity<?> getUserFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	public ResponseEntity<?> getallUserFallback(){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	public ResponseEntity<?> deleteUserFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	public ResponseEntity<?> allBookingsFallback(){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	public ResponseEntity<?> getBookingFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	public ResponseEntity<?> deleteBookingFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	public ResponseEntity<?> allReviewsFallback(){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	public ResponseEntity<?> reviewFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	public ResponseEntity<?> ratingFallback(String id){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	public ResponseEntity<?> incomeFallback(){
		return new ResponseEntity<>("Something went wrong! Try again after some time ", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	/******************************************************************************************************************************/
}
