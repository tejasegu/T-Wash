package com.twash.userservice.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.twash.userservice.exception.*;
import com.twash.userservice.model.Users;
import com.twash.userservice.service.SequenceGeneratorService;
import com.twash.userservice.service.UsersDaoImpl;
import io.swagger.v3.oas.annotations.Operation;

/**
 * This is a Rest Controller class for UserController
 *
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	@Autowired
	private UsersDaoImpl usersdao;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	
	//To Insert Users Into DataBase
	@Operation(summary = "Adds a new user")
	@PostMapping("/add")
	public ResponseEntity<String> createUsers( @Validated @RequestBody Users user) {
		try {
			usersdao.addUsers(user);
			return ResponseEntity.ok("User Registered Suceesfully with Id:"+user.getId());
			}
			catch (Exception e) {
			 sequenceGeneratorService.decreamentSequence(Users.SEQUENCE_NAME);
				 return new ResponseEntity<>("Email or MobileNumber Already Exist",HttpStatus.BAD_REQUEST);
			}
	}
	
	
	
	  //To Get All Users Data From DataBase
	  @Operation(summary = "View a list of all users")
	  @GetMapping("/get") 
	  public List<Users> getAllUsers() { 
		  return usersdao.getAllUsers(); 
		  
	}
	  
	  //To Get Specific User Data By ID From DataBase
	  @Operation(summary = "View a user details by Id")
	  @GetMapping("/find/{id}") 
	  public ResponseEntity<Users> findUserById(@PathVariable(value="id")long id)throws ResourceNotFoundException{
		  Users users=usersdao.getUserbyId(id).orElseThrow(()->new ResourceNotFoundException("User Not Found For This ID::"+id)); 
		  return ResponseEntity.ok(users); 
		  
	}
	 
	
	  //To Get Users Data By Role From DataBase
	  @Operation(summary ="View a list of users by Role")
	  @GetMapping("/get/role/{role}") 
	  public ResponseEntity<List<Users>> findUserByRole(@PathVariable(value="role")String role)throws ResourceNotFoundException{ 
		  List<Users> users=usersdao.getUsersbyRole(role).orElseThrow(()->new ResourceNotFoundException("Users Not Found For This Role::"+role)); 
		  return ResponseEntity.ok(users); 
		  
	}
	 
	  //To Update User By Id
	  @Operation(summary = "Update the user by Id")
	  @PutMapping("/update/{id}")
	  public ResponseEntity<String> updateUserById(@PathVariable(value="id")long id, @Validated @RequestBody Users user){
		 try {
			
				 Users users=usersdao.updateUserbyId(id, user);
				  return ResponseEntity.ok("User Updated Sucessfully For Id"+users.getId());
				 
		 }
		 catch(Exception e) {
			return e.getMessage().contains("duplicate") ? new ResponseEntity<>("Email Or Mobile Number already Exists",HttpStatus.INTERNAL_SERVER_ERROR): new ResponseEntity<>("User Not Found for ID: "+id,HttpStatus.NO_CONTENT);
		 } 
		  
	  }
 
	  
	  //To Delete Specific User By Id
	  @Operation(summary = "Delete a user by Id")
	  @DeleteMapping("/delete/{id}")
	  
	  public ResponseEntity<String> deleteUserById(@PathVariable(value="id")long id) {
		  Optional<Users> use=usersdao.getUserbyId(id);
		  try {
			  if(use.isEmpty()) {
				  return new ResponseEntity<>("User Not Found For ID:"+id, HttpStatus.NOT_FOUND);
		 }
			  usersdao.deleteUserbyId(id);
			  return ResponseEntity.ok("User Deleted Sucessfully For Id"+id);
			 
	  }
		  catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  //To Get The Specific User Details By Email
	@Operation(summary ="View user details by Email")
	 @GetMapping("/get/email/{email}")
	 public  ResponseEntity<Users> findUserByMail(@PathVariable(value= "email")String email) throws ResourceNotFoundException{
		 Users users=usersdao.getUserbyEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found For This Email ::"+email));
		 return ResponseEntity.ok(users);
	}
	
	 //To Get The Specific Washer Details By Area
	@Operation(summary ="View washers by area")
	 @GetMapping("/get/washer/{area}")
	 public  ResponseEntity<List<Users>> findWasherByArea(@PathVariable(value= "area")String area) throws ResourceNotFoundException{
			 List<Users> users=usersdao.getWashersbyArea(area);
			 return ResponseEntity.ok(users);
		}
	
	//To Get Set The Specific Washer Sttatus
		@Operation(summary ="Set Status")
		 @PutMapping("/set/{id}/{status}")
		 public  ResponseEntity<String> setWasherStatus(@PathVariable(value= "status")String status, @PathVariable(value= "id")long id) {
				usersdao.setStatus(id, status);
				 return ResponseEntity.ok("Updated Scessfully");
			}
		 
}
