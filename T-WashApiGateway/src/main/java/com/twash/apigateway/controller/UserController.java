package com.twash.apigateway.controller;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.twash.apigateway.model.Addon;
import com.twash.apigateway.model.Bookings;
import com.twash.apigateway.model.Notifications;
import com.twash.apigateway.model.Reviews;
import com.twash.apigateway.model.Users;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@EnableHystrix
public class UserController {
	

	/*********************************************URLS***********************************************************************/
	
	
	String GETUSER="http://t-washuserservice/users/find/";
	String UPDATEUSER="http://t-washuserservice/users/update/";
	String NEWREVIEW="http://t-washuserservice/review";
	String GETNOTIFICATION="http://t-washnotificationservice/notification/find/";
	String COUNTNOTIFICATION="http://t-washnotificationservice/notification/count/";
	String NEWCASHBOOK="http://t-washbookingservice/bookings/cash";
	String NEWONLINEBOOK="http://t-washbookingservice/booking/online";
	String BOOKBYUSER="http://t-washbookingservice/bookings/find/user/";
	String SCHEDULEBOOKBYUSER="http://t-washbookingservice/bookings/scheduled/user/";
	String GETIMG="http://t-washbookingservice/images/";
	String ADDON="http://t-washnotificationservice/addon";
	
	
	/************************************************************************************************************************/
	
	
	@Autowired
	RestTemplate restemplate;


	/*get user*/
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getuser( @PathVariable("id") String id) {
	try {
	  Users use=restemplate.getForObject(GETUSER+id, Users.class);
		return ResponseEntity.ok(use) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
	                   }
	}
	
	/*Update user*/
	 @PutMapping("/update/{id}")
	  public ResponseEntity<String> updateUserById(@PathVariable(value="id")long id, @Validated @RequestBody Users user){
		 try {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Users> entity = new HttpEntity<Users>(user,headers);
		 String message=restemplate.exchange(UPDATEUSER+id, HttpMethod.PUT, entity, String.class).getBody();
		 return ResponseEntity.ok(message);
		 }
		 catch(Exception e){
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 
	 }
	 
	 /*Add review*/
	 @PostMapping("/review")
		public ResponseEntity<String> createReview(  @RequestBody Reviews review) {
			try {
				 HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<Reviews> entity = new HttpEntity<Reviews>(review,headers);
				 String message=restemplate.exchange(NEWREVIEW, HttpMethod.POST, entity, String.class).getBody();
				 return ResponseEntity.ok(message);
				}
				catch (Exception e) {
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
				}
		}
	 
	 
	 /*Get notifications*/
	 @GetMapping("/notification/find/{userid}")
		public ResponseEntity<?> findNotificationById (@PathVariable("userid") String userid) {
			try {
				Notifications[] response= restemplate.getForObject(GETNOTIFICATION+userid, Notifications[].class);
			//	List<Notifications> notify=(List<Notifications>) restemplate.getForObject(GETNOTIFICATION+userid, Notifications.class);
					return ResponseEntity.ok(response) ;
			}
			catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	 
	 
	 /*Get notification count*/
	 @GetMapping("/notification/count/{userid}")
		public ResponseEntity<?> getCount(@PathVariable("userid") String userid){
			try {
				 int count=restemplate.getForObject( COUNTNOTIFICATION+userid, int.class);
				 return ResponseEntity.ok(count);
		}
			catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 
	 /*Post Booking by cash*/
	 @PostMapping("/bookings/cash")
	 	public ResponseEntity<String> createBooking( @Validated @RequestBody Bookings booking) {
	 		try {
	 			HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<Bookings> entity = new HttpEntity<Bookings>(booking,headers);
				 String message=restemplate.exchange(NEWCASHBOOK, HttpMethod.POST, entity, String.class).getBody();
				 return ResponseEntity.ok(message);
	 			
	 			}
	 			catch (Exception e) {
	 			 
	 				 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	 			}
	 	}
	 
	 
	 /*New Online Booking*/
	 @PostMapping("/bookings/online")
	 	public ResponseEntity<String> createBookingonline( @Validated @RequestBody Bookings booking) {
	 		try {
	 			HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<Bookings> entity = new HttpEntity<Bookings>(booking,headers);
				 String message=restemplate.exchange(NEWONLINEBOOK, HttpMethod.POST, entity, String.class).getBody();
				 return ResponseEntity.ok(message);
	 			
	 			}
	 			catch (Exception e) {
	 			 
	 				 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	 			}
	 	}
	 
	 
	 /*Get list of bookings by userid*/
	 @GetMapping("/bookings/find/user/{id}") 
	  public ResponseEntity<?> findBookingByUserId(@PathVariable(value="id")String userid){
		  
		try {
			
			Bookings[] response= restemplate.getForObject(BOOKBYUSER+userid, Bookings[].class);
					return ResponseEntity.ok(response) ;
			
		}
		catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
		}
		  
	}
	
	 /*Get list of Scheduled bookings by userid*/
	 
	 @GetMapping("/bookings/scheduled/user/{id}") 
	 
	 public ResponseEntity<?> findScheduledBookingByUserId(@PathVariable(value="id")String userid){
		  
			try {
				
				Bookings[] response= restemplate.getForObject(SCHEDULEBOOKBYUSER+userid, Bookings[].class);
						return ResponseEntity.ok(response) ;
				
			}
			catch (Exception e) {
				 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
			}
			  
		}
	 
	 
	 /*To get image*/
		@GetMapping("/images/{title}")
		public ResponseEntity<?> getPhoto(@PathVariable String title) {
		  try {
			  String img=restemplate.getForObject(GETIMG+title, String.class);
				return ResponseEntity.ok(img) ;
		  }
		  catch (Exception e) {
				 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
			}
		  
		}
		
		
		/* To Get Addons*/
		 @GetMapping("/addon") 
		 public ResponseEntity<?> getAddons(){
			  
				try {
					
					Addon[] response= restemplate.getForObject(ADDON, Addon[].class);
					return ResponseEntity.ok(response) ;
				}
				catch (Exception e) {
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
				}
				  
			}
}
