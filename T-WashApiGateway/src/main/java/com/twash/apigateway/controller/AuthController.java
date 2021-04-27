package com.twash.apigateway.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.twash.apigateway.model.JwtResponse;
import com.twash.apigateway.model.LoginRequest;
import com.twash.apigateway.model.MongoUserDetails;
import com.twash.apigateway.model.Users;
import com.twash.apigateway.security.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RestTemplate restemplate;
	
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		MongoUserDetails userDetails = (MongoUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles));
	}
    
	@PostMapping("/signup")
	
	public ResponseEntity<?> registerUser(@Validated @RequestBody Users user) {
		 try {
			 System.out.println("i0000n");
			 HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Users> entity = new HttpEntity<Users>(user,headers);
			 String message=restemplate.exchange("http://t-washuserservice/users/add", HttpMethod.POST, entity, String.class).getBody();
			 System.out.println(message);
			 return ResponseEntity.ok(message);
			 }
			 catch(Exception e){
				 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			 }
	}

}