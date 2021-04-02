package com.twash.userservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import com.twash.userservice.model.Users;
import com.twash.userservice.repository.UsersRepository;


@DataMongoTest
public class UsersRepositoryTest {
	@Autowired
	UsersRepository repotest;
	
	long id = 1;
	String Email = "lovel";
    String Name="teja";
    Users user = new Users(id, Name,"M","teja",Email, 9247,"Admin");
    
	
	@Test
	void itShouldselectUserByEmail() {
		 // Given
         
		repotest.save(user);
        Optional<Users> optionaluser=repotest.findByEmail(Email);
        
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
		Optional<Users> optionaluser=repotest.findByEmail(Email);
		
        assertThat(optionaluser.get().getName().equals(Name));
		
	}
	@Test
	void  itShouldselectUserByRole() {
		repotest.save(user);
		Optional<List<Users>> optionaluser=repotest.findByRole("Admin");
		assertThat(optionaluser).isPresent();
	}
	@Test
	void itShouldNotSaveWhenEmailIsPresent() {
		repotest.save(user);
		String Email2="lovel";
		String Name2="Teja";
		
		Users user1 = new Users(2, Name2,"M","teja",Email2, 92474,"Admin");
		assertThatThrownBy(() -> repotest.save(user1))
        .isInstanceOf(DuplicateKeyException.class);
}

	}

