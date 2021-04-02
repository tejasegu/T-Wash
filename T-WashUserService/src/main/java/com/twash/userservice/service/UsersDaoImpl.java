package com.twash.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.twash.userservice.model.Users;
import com.twash.userservice.repository.UsersDaoInterface;
import com.twash.userservice.repository.UsersRepository;
@Service
public class UsersDaoImpl implements UsersDaoInterface {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	@Override
	public Users addUsers(Users user)  {
		user.setId(sequenceGeneratorService.generateSequence(Users.SEQUENCE_NAME));
		user.setPassword(passwordEncoderService.passwordEncoder().encode(user.getPassword()));
		return usersRepository.save(user);
		
		
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<Users> getUserbyId(long Id) {
		
		return usersRepository.findById(Id);
	}

	@Override
	public Optional<List<Users>> getUsersbyRole(String Role) {
		
		return usersRepository.findByRole(Role);
	}

	@Override
	public Users updateUserbyId(long Id, Users user) {
		Optional<Users> users=usersRepository.findById(Id);
		Users userstosave=users.get();
		userstosave.setGender(user.getGender());
		userstosave.setMobilenumber(user.getMobilenumber());
		userstosave.setName(user.getName());
		return usersRepository.save(userstosave);
	}

	@Override
	public long deleteUserbyId(long Id) {
		usersRepository.deleteById(Id);
		
       return Id;
	}

	@Override
	public Optional<Users> getUserbyEmail(String Email) {
		
		return usersRepository.findByEmail(Email);
	}

}
