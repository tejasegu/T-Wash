package com.twash.apigateway.service;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.twash.apigateway.model.MongoUserDetails;
import com.twash.apigateway.model.Users;
@Service
public class UserService implements UserDetailsService {
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 final String uri = "http://localhost:1111/users/get/email/"+username;

		    RestTemplate restTemplate = new RestTemplate();
		    Users user = restTemplate.getForObject(uri,  Users .class);
		
		return MongoUserDetails.build(user);
	}

	 
}


