package com.twash.apigateway.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.twash.apigateway.model.Bookings;
import com.twash.apigateway.model.Users;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/washers")
public class WasherController {

	@Autowired
	RestTemplate restemplate;

	/********************************************URLS************************************************************************/
	
		String BOOKBYWASHER="http://t-washbookingservice/bookings/find/washer/";
		String SetIMG="http://t-washbookingservice/images/";
		String SETSTATUS="http://t-washbookingservice/bookings/update/";	
		String SCHEDULEBOOKBYWASHER="http://t-washbookingservice/bookings/scheduled/washer/";
		String WASHERBYAREA="http://t-washuserservice/users/get/washer/";
		String SETACCSTATUS="http://t-washuserservice/users/set/";
		String SETIMG="http://t-washbookingservice/images/";
		String GETIMG="http://t-washbookingservice/images/";

    /************************************************************************************************************************/
		
		

		/*Get list of bookings by washerid*/
		 
		 @GetMapping("/bookings/find/washer/{id}") 
		  public ResponseEntity<?> findBookingByUserId(@PathVariable(value="id")String washerid){
			  
			try {
				
				Bookings[] response= restemplate.getForObject(BOOKBYWASHER+washerid, Bookings[].class);
						return ResponseEntity.ok(response) ;
				
			}
			catch (Exception e) {
				 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
			}
			  
		}
		 
		 /*To Update OrderStatus By OrderId*/
		  @PutMapping("/bookings/update/{id}/{status}")
		  public ResponseEntity<?> updateStatusById(@PathVariable(value="id")long id, @PathVariable(value="status") String status){
			 try {
				 Bookings book=new Bookings();
				 HttpHeaders headers = new HttpHeaders();
				 HttpEntity<Bookings> entity = new HttpEntity<Bookings>(book,headers);
				 String message=restemplate.exchange(SETSTATUS+id+"/"+status,HttpMethod.PUT , entity, String.class).getBody();
				 return ResponseEntity.ok(message);
				 }
				 catch(Exception e){
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				 }
			  
		  }
		  
		/*Get list of Scheduled bookings by washerid*/
			 
			 @GetMapping("/bookings/scheduled/washer/{id}") 
			 
			 public ResponseEntity<?> findScheduledBookingByUserId(@PathVariable(value="id")String washerid){
				  
					try {
						
						Bookings[] response= restemplate.getForObject(SCHEDULEBOOKBYWASHER+washerid, Bookings[].class);
								return ResponseEntity.ok(response) ;
						
					}
					catch (Exception e) {
						 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
					}
					  
				}
			 		 
			 
			 /*Get list of washers by id*/
 			 
             @GetMapping("/area/{area}") 
			 
			 public ResponseEntity<?> getWashersByArea(@PathVariable(value="area")String area){
				  
					try {
						
						Users[] response= restemplate.getForObject(WASHERBYAREA+area, Users[].class);
								return ResponseEntity.ok(response) ;
						
					}
					catch (Exception e) {
						 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
					}
					  
				}
             
             
          /*Set Order Status*/   
          @PutMapping("/set/{id}/{status}")
   		  public ResponseEntity<?> setAccStatus(@PathVariable(value="id")long id, @PathVariable(value="status") String status){
   			 try {
   				 Users user=new Users();
   				 HttpHeaders headers = new HttpHeaders();
   				 HttpEntity<Users> entity = new HttpEntity<Users>(user,headers);
   				 String message=restemplate.exchange(SETACCSTATUS+id+"/"+status,HttpMethod.PUT , entity, String.class).getBody();
   				 return ResponseEntity.ok(message);
   				 }
   				 catch(Exception e){
   					 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   				 }
   			  
   		  }
             
             
           /*To Upload Image*/
   		  @PostMapping("/images/{id}")
   		  public ResponseEntity<?> setImage(@PathVariable(value="id")long id, @RequestParam("image") MultipartFile image){
   			 try {
   				 
   				 HttpHeaders headers = new HttpHeaders();
   		        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
   				 HttpEntity<MultipartFile> entity = new HttpEntity<MultipartFile>(image,headers);
   				 String message=restemplate.exchange(SETIMG+id+"/add",HttpMethod.POST , entity, String.class).getBody();
   				 return ResponseEntity.ok(message);
   				 }
   				 catch(Exception e){
   					 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   				 }
   			  
   		  }
   		  
   		  
   		  /*To Get Image*/
   		 @GetMapping("/images/{id}") 
		 
		 public ResponseEntity<?> getImage(@PathVariable(value="id")String id){
			  
				try {
					
					String response= restemplate.getForObject(GETIMG+id, String.class);
							return ResponseEntity.ok(response) ;
					
				}
				catch (Exception e) {
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
				}
				  
			}
   		  
   		  
}
