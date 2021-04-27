package com.twash.userservice;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import com.twash.userservice.model.Users;
import com.twash.userservice.repository.UsersRepository;


@DataMongoTest
public class UsersRepositoryTest {
	@Autowired
	UsersRepository repotest;
	
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
    
	
	@Test
	void itShouldselectUserByEmail() {
		 // Given
         
		repotest.save(user);
        Optional<Users> optionaluser=repotest.findByEmail(email);
        
        assertThat(optionaluser).isPresent();

	}
	@Test
	void itShouldnotselectUserIfEmailNotPresent() {
		 // Given
         
		repotest.save(user);
        Optional<Users> optionaluser=repotest.findByEmail("lo");
        
        assertThat(optionaluser).isNotPresent();

	}
	@Test
	void itShouldMatchUserDataByEmail() {
		repotest.save(user);
		Optional<Users> optionaluser=repotest.findByEmail(email);
		
        assertThat(optionaluser.get().getName().equals(name));
		
	}
	@Test
	void  itShouldselectUserByRole() {
		repotest.save(user);
		Optional<List<Users>> optionaluser=repotest.findByRole("Admin");
		assertThat(optionaluser).isPresent();
	}
	
	@Test
	void itShouldnotselectUserIfRoleNotPresent() {
		 // Given
         
		repotest.save(user);
        List<Users> optionaluser=repotest.findByRole("user").get();
        
        assertThat(optionaluser).isEmpty();

	}
	
	@Test
	void itShouldMatchUserDataByRole() {
		repotest.save(user);
		List<Users> optionaluser=repotest.findByRole("Admin").get();
		
        assertThat(optionaluser.stream().anyMatch(o->o.getName().equals(name)));
		
	}

	}

