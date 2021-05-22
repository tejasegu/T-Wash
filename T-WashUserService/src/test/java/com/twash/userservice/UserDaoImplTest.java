package com.twash.userservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.twash.userservice.model.Users;
import com.twash.userservice.repository.UsersRepository;
import com.twash.userservice.service.PasswordEncoderService;
import com.twash.userservice.service.SequenceGeneratorService;
import com.twash.userservice.service.UsersDaoImpl;
@DataMongoTest

public class UserDaoImplTest {

	@Mock
	private UsersRepository usersRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	@Mock
	private PasswordEncoderService passwordEncoderService;
	@InjectMocks
	private UsersDaoImpl daotest;
	
	long id = 1;
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

	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	public void addUserstest()  {
		
		daotest.addUsers(user);
		verify(usersRepository, times(1)).save(user);
		
	}
	
	@Test
	public void getAllUsers() {
		 
		 List<Users> allusers=new ArrayList<Users>();
		allusers.add(user);
		allusers.add(user1);
		
		
		when(daotest.getAllUsers()).thenReturn(allusers);
	
		List<Users> use=daotest.getAllUsers();
		assertThat(use).isNotEmpty();
	}
	
	@Test
	public void getAllUsersWhenEmpty() {
		List<Users> allusers=new ArrayList<Users>();
		when(daotest.getAllUsers()).thenReturn(allusers);
	
		List<Users> use=daotest.getAllUsers();
		assertThat(use).isEmpty();
	}
	
	@Test
	public void getUserById() {
		Optional<Users> use=Optional.of(new Users(id, name,gender,password,email,number,town,area,role,status));
		when(daotest.getUserbyId(1)).thenReturn(use);
		Users used=daotest.getUserbyId(1).get();
		assertThat(used.getName()).isEqualTo(name);
	}
	
	@Test
	public void donotgetUsersById() {
		when(daotest.getUserbyId(1)).thenReturn(Optional.empty());
		Optional<Users> used=daotest.getUserbyId(1);
		assertThat(used).isEmpty();
	}
	@Test
	public void getUserByRole() {
		 List<Users> allusers=new ArrayList<Users>();
			allusers.add(user);
			allusers.add(user1);
		
		when(daotest.getUsersbyRole(role)).thenReturn(Optional.of(allusers));
		List<Users> used=daotest.getUsersbyRole(role).get();
		assertThat(used).isNotEmpty();
	}
	@Test
	public void donotgetUsersByRole() {
		when(daotest.getUsersbyRole(role)).thenReturn(Optional.empty());
		Optional<List<Users>> used=daotest.getUsersbyRole(role);
		assertThat(used).isEmpty();
	}
	
	@Test
	public void updateUserstest()  {
		Users user = new Users(id, name,gender,password,email,number,town,area,role,status);
		Users user1 = new Users(id, "t","m","password","email",122,"town","area","role","status");
		when(usersRepository.findById(id)).thenReturn(Optional.of(user1));
		Users expected=daotest.updateUserbyId(id, user);
		assertThat(expected).isNotNull();
		assertThat(expected.getMobilenumber()).isEqualTo(number);
	}
	@Test
	public void deleteById() {
		Users user = new Users(id, name,gender,password,email,number,town,area,role,status);
		when(usersRepository.findById(id)).thenReturn(Optional.of(user));
		daotest.deleteUserbyId(id);
		verify(usersRepository).deleteById(id);
	}
	@Test
	public void getUserByEmail() {
		Optional<Users> use=Optional.of(new Users(id, name,gender,password,email,number,town,area,role,status));
		when(daotest.getUserbyEmail(email)).thenReturn(use);
		Users used=daotest.getUserbyEmail(email).get();
		assertThat(used.getName()).isEqualTo(name);
	}
	
	@Test
	public void donotgetUsersByEmail() {
		when(daotest.getUserbyEmail(email)).thenReturn(Optional.empty());
		Optional<Users> used=daotest.getUserbyEmail(email);
		assertThat(used).isEmpty();
	}
	@Test
	public void getWashersByArea() {
		  Users user2= new Users(id, name,gender,password,email,number,town,area,"ROLE_WASHER",status);
		    Users user3 = new Users(1, "t","m","password","email",122,"town","area","ROLE_WASHER","status");
		
		 List<Users> allusers=new ArrayList<Users>();
			allusers.add(user2);
			allusers.add(user3);
		
		when(usersRepository.findByRole("ROLE_WASHER")).thenReturn(Optional.of(allusers));
		List<Users> used=daotest.getWashersbyArea(area);
		assertThat(used).isNotEmpty();
	}
	@Test
	public void donotGetWashersByArea() {
		  Users user2= new Users(id, name,gender,password,email,number,town,area,"ROLE_WASHER",status);
		    Users user3 = new Users(1, "t","m","password","email",122,"town","area","Washer","status");
		
		 List<Users> allusers=new ArrayList<Users>();
			allusers.add(user2);
			allusers.add(user3);
		
		when(usersRepository.findByRole("ROLE_WASHER")).thenReturn(Optional.of(allusers));
		List<Users> used=daotest.getWashersbyArea("abc");
		assertThat(used).isEmpty();
	}
	
}
