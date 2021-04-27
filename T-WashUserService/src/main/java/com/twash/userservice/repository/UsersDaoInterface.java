package com.twash.userservice.repository;

import java.util.List;
import java.util.Optional;

import com.twash.userservice.model.Users;

public interface UsersDaoInterface {
	public Users addUsers(Users user);
	public List<Users> getAllUsers();
	public Optional<Users> getUserbyId(long Id);
	public Optional<List<Users>> getUsersbyRole(String Role);
	public Users updateUserbyId(long Id, Users user);
	public long deleteUserbyId(long Id);
	public Optional<Users> getUserbyEmail(String Email);
	public List<Users> getWashersbyArea(String area);
	public void setStatus(long id, String status);
}
