package com.twash.bookingservice.controller;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.twash.bookingservice.model.Bookings;
import com.twash.bookingservice.service.BookingsDaoImpl;
import com.twash.bookingservice.service.SequenceGeneratorService;
import com.twash.bookingservice.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;

/**
 * This is a Rest Controller class for BookingController
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class BookingsController {
   @Autowired
   private BookingsDaoImpl bookingdaoimpl;
   @Autowired
   private SequenceGeneratorService sequenceGeneratorService;
   
    //To Insert Booking Into DataBase
 	@Operation(summary = "Adds a new booking")
 	@PostMapping("/bookings/cash")
 	public ResponseEntity<String> createBooking( @Validated @RequestBody Bookings booking) {
 		try {
 			bookingdaoimpl.addBooking(booking);
 			return ResponseEntity.ok("Booking Completed Suceesfully with Id:"+booking.getId());
 			}
 			catch (Exception e) {
 			 sequenceGeneratorService.decreamentSequence(Bookings.SEQUENCE_NAME);
 				 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
 			}
 	}
 	
 	
 	 //To Get All BookingsData From DataBase
	  @Operation(summary = "View a list of all bookings")
	  @GetMapping("/bookings") 
	  public List<Bookings> getAllBookings() { 
		  return bookingdaoimpl.getAllBookings(); 
		  
	}
	  
	  //To Get Specific  Booking By ID From DataBase
	  @Operation(summary = "View a booking details by Id")
	  @GetMapping("/bookings/find/{id}") 
	  public ResponseEntity<Bookings> findBookingById(@PathVariable(value="id")long id)throws ResourceNotFoundException{
		  Bookings booking=bookingdaoimpl.getBookingsbyId(id).orElseThrow(()->new ResourceNotFoundException("Bookings Not Found For This ID::"+id)); 
		  return ResponseEntity.ok(booking); 
		  
	}
	  //To Get Specific  Bookinglist By UserID From DataBase
	  @Operation(summary = "View a booking details by UserId")
	  @GetMapping("/bookings/find/user/{id}") 
	  public ResponseEntity<?> findBookingByUserId(@PathVariable(value="id")String userid)throws ResourceNotFoundException{
		  
		 List<Bookings> booking=bookingdaoimpl.getBookingsByUserId(userid);
		 if(booking.isEmpty()) {
			  return ResponseEntity.ok("No Booking Found");
		  }
		 else {
		 return ResponseEntity.ok(booking); 
		 }
		  
	}
	//To Get Specific  Bookinglist By WasherID From DataBase
	  @Operation(summary = "View a booking details by washerId")
	  @GetMapping("/bookings/find/washer/{id}") 
	  public ResponseEntity<?> findBookingByWasherId(@PathVariable(value="id")String washerid)throws ResourceNotFoundException{
		  List<Bookings> booking=bookingdaoimpl.getBookingsByWasherId(washerid);
		  if(booking.isEmpty()) {
			  return ResponseEntity.ok("No Booking Found");
		  }
		  else {
		  return ResponseEntity.ok(booking); 
		  }
		  
	}
 	
	  //To Update OrderStatus By OrderId
	  @Operation(summary = "Update the OrderStatus by Id")
	  @PutMapping("/bookings/update/{id}/{status}")
	  public ResponseEntity<?> updateStatusById(@PathVariable(value="id")long id, @PathVariable(value="status") String status){
		 try {
			 if(bookingdaoimpl.getBookingsbyId(id).isPresent()) {
			
				 bookingdaoimpl.updateBookingStatusbyId(id, status);;
				  return ResponseEntity.ok("Booking Updated Sucessfully ");
			 }
			 else {
				 return ResponseEntity.ok("No Booking Found");
			 }
		 }
		 catch(Exception e) {
			return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
		 } 
		  
	  }
	  
	  //To Delete Specific Booking By Id
	  @Operation(summary = "Delete a Booking by Id")
	  @DeleteMapping("/bookings/delete/{id}")
	  
	  public ResponseEntity<String> deleteBookingById(@PathVariable(value="id")long id) {
		  Optional<Bookings> book=bookingdaoimpl.getBookingsbyId(id);
		  try {
			  if(book.isEmpty()) {
				  return new ResponseEntity<>("Booking Not Found For ID:"+id, HttpStatus.NOT_FOUND);
		 }
			  bookingdaoimpl.deleteBookingsbyId(id);
			  return ResponseEntity.ok("Booking Deleted Sucessfully For Id"+id);
			 
	  }
		  catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  
	  //To Get TotalIncome 
	  @Operation(summary = "Get Total Income")
	  @GetMapping("/bookings/income")
	  public  ResponseEntity<Long> getTotalIncome(){
			/*
			 * try { long income= bookingdaoimpl.getTotalIncome(); return
			 * ResponseEntity.ok(income); } catch(Exception e) { long a=0; return new
			 * ResponseEntity<>(a, HttpStatus.NO_CONTENT); }
			 */
		  return  ResponseEntity.ok(bookingdaoimpl.getTotalIncome());
	  }
	  
	  //To Get scheduled bookings for user
	  @Operation(summary = "Get scheduled bookings")
	  @GetMapping("/bookings/scheduled/user/{id}") 
	  public ResponseEntity<?> findscheduledBookingByUserId(@PathVariable(value="id")String userid)throws ResourceNotFoundException{
		  
		 List<Bookings> booking= bookingdaoimpl.getScheduledBookingsByUserId(userid); 
		 if(booking.isEmpty()) {
			  return ResponseEntity.ok("No Booking Found");
		  }
		 else {
		 return ResponseEntity.ok(booking); 
		 }
		  
	}
	  
	//To Get scheduled bookings for Washer
	  @Operation(summary = "Get scheduled bookings")
	  @GetMapping("/bookings/scheduled/washer/{id}") 
	  public ResponseEntity<?> findscheduledBookingBywasherId(@PathVariable(value="id")String washerid)throws ResourceNotFoundException{
		  
		 List<Bookings> booking= bookingdaoimpl.getScheduledBookingsByWasherId(washerid); 
		 if(booking.isEmpty()) {
			  return ResponseEntity.ok("No Booking Found");
		  }
		 else {
		 return ResponseEntity.ok(booking); 
		 }
		  
	}
	  
	  

}
