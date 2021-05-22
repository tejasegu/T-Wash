package com.twash.userservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;


import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twash.userservice.controller.UserController;
import com.twash.userservice.model.Users;
import com.twash.userservice.service.PasswordEncoderService;
import com.twash.userservice.service.SequenceGeneratorService;
import com.twash.userservice.service.UsersDaoImpl;




@WebMvcTest(UserController.class)
public class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UsersDaoImpl service;
	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;
	@MockBean
	private PasswordEncoderService passwordEncoderService;
	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	
	long id = 21030;
	 String name="teja";
	 String gender="M";
	 String password="teja";
   String email = "lovel";
   long   number=9247;
   String town="pkl";
   String area="policestation";
   String role="Admin";
   String status="Active";
   Users user = new Users(id, name,gender,password,email,number,town,area,role,status);
   Users user1 = new Users(1, "t","m","password","email",122,"town","area","role","status");
   ObjectMapper mapper = new ObjectMapper();

   
  

  
   protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	@Test
	public void  createUsersTest() throws Exception {
		 mapper.writeValue(new File("user.json"), user);
		 String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users")
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "User Registered Suceesfully with Id:"+user.getId());
	   
	}
	
	@Test
	public void getUsersList() throws Exception {
		 
	   String uri = "/users";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	    long l= mvcResult.getResponse().getContentLength();
	    assertThat(l>0);
	}
	
	@Test
	public void updateUser() throws Exception {
		 Users user1 = new Users(21030, "t","m","password","email",122,"town","area","role","status");
		 when(service.updateUserbyId(id, user1)).thenReturn(user1);
	   String uri = "/users/update/";
	   mapper.writeValue(new File("user1.json"), user1);
		 String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
	
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri+id)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content,"User Updated Sucessfully For Id"+user.getId());
	}
}
