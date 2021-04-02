package com.twash.userservice.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.twash.userservice.exception.*;
import com.twash.userservice.model.Users;
import com.twash.userservice.service.SequenceGeneratorService;
import com.twash.userservice.service.UsersDaoImpl;

/**
 * This is a Rest Controller class for UserController
 *
 */
@RestController
public class UserController {
	@Autowired
	private UsersDaoImpl usersdao;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	//To Insert Users Into DataBase
	@PostMapping("/users")
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
	  
	  @GetMapping("/users") public List<Users> getAllUsers() { 
		  return usersdao.getAllUsers(); }
	  
	  //To Get Specific User Data By ID From DataBase
	  @GetMapping("/users/find/{id}") public ResponseEntity<Users> findUserById(@PathVariable(value="id")long id)throws ResourceNotFoundException{
		  Users users=usersdao.getUserbyId(id).orElseThrow(()->new
	      ResourceNotFoundException("User Not Found For This ID::"+id)); return
	      ResponseEntity.ok(users); }
	 
	
	  //To Get Users Data By Role From DataBase
	  
	  @GetMapping("/users/get/{role}") 
	  public ResponseEntity<List<Users>> findUserByRole(@PathVariable(value="role")String role)throws ResourceNotFoundException{ 
		  List<Users> users=usersdao.getUsersbyRole(role).orElseThrow(()->new
	      ResourceNotFoundException("Users Not Found For This Role::"+role)); return
	      ResponseEntity.ok(users); }
	 
	  //To Update User By Id
	  @PutMapping("/users/update/{id}")
	  public ResponseEntity<String> updateUserById(@PathVariable(value="id")long id, @Validated @RequestBody Users user){
		  try {
			  Users users=usersdao.updateUserbyId(id, user);
			  return ResponseEntity.ok("User Updated Sucessfully For Id"+users.getId());

		  }
		  catch (Exception e) {
		  
		  return new ResponseEntity<>("User Not Found For ID:"+id, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		  
	  }
	  
	  //To Delete Specific User By Id
	  @DeleteMapping("/users/delete/{id}")
	  
	  public ResponseEntity<String> deleteUserById(@PathVariable(value="id")long id) {
		  try {
		  usersdao.deleteUserbyId(id);
		  return ResponseEntity.ok("User Deleted Sucessfully For Id"+id);
	  }
		  catch (Exception e) {
			  return new ResponseEntity<>("User Not Found For ID:"+id, HttpStatus.NOT_FOUND);
		  }
	  }
	  
	  //To Get The Specific User Details By Email
	 @GetMapping("/users/{email}")
	 public  ResponseEntity<Users> findUserByMail(@PathVariable(value= "email")String email) throws ResourceNotFoundException{
		 Users users=usersdao.getUserbyEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found For This Email ::"+email));
		 return ResponseEntity.ok(users);
	}
	
}