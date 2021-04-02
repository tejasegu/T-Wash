package com.twash.userservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")

/**
 * The Users Class Is A Model Class For The Users 
 * Schema Which Is To Be Present In Database
 */
public class Users {

	@Transient //  By Default All Private Fields Are Mapped To The Document,  This Annotation Excludes This Field Where It Is Applied From Being Stored In The Database
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id // Generates A Unique Id In MongoDB
	private long id;
	private String name;
	private String gender;
	private String password;
    @Indexed(unique=true) //Enable Indexing To Insert Unique Fields
	private String email;
    @Indexed(unique=true) // Enable Indexing To Insert Unique Fields
	private long mobilenumber;
	private String role;
	//All Argument Constructor
	public Users(long id, String name, String gender, String password, String email, long mobilenumber, String role) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.mobilenumber = mobilenumber;
		this.role = role;
	}
	//No Argument Constructor
	public Users() {
		super();
		
	}
	//Getters And Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	

	
}
