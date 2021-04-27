
package com.twash.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		user.setPassword(passwordEncoderService.passwordEncoders(user.getPassword()));
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
		userstosave.setMobilenumber(user.getMobilenumber());
		userstosave.setName(user.getName());
		userstosave.setEmail(user.getEmail());
		userstosave.setArea(user.getArea());
		usersRepository.save(userstosave);
		return userstosave;
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

	@Override
	public List<Users> getWashersbyArea(String area) {
		List<Users> washers=usersRepository.findByRole("ROLE_WASHER").get();
		List<Users> washerbyarea=washers.stream().filter(o->o.getArea().equals(area) && "Active".equals(o.getStatus())).collect(Collectors.toList());
		return washerbyarea;
	}

	@Override
	public void setStatus(long id, String status) {
		Users user=usersRepository.findById(id).get();
		user.setStatus(status);
		usersRepository.save(user);
		
	}

}
