package com.twash.userservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="Users")

/**
 * The Users Class Is A Model Class For The Users 
 * Schema Which Is To Be Present In Database
 */
public class Users {

	@Transient //  By Default All Private Fields Are Mapped To The Document,  This Annotation Excludes This Field Where It Is Applied From Being Stored In The Database
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id // Generates A Unique Id In MongoDB
	@ApiModelProperty(notes = "The database generated user ID")
	private long id;
	@ApiModelProperty(notes = "The name of the user")
	private String name;
	@ApiModelProperty(notes = "The gender of the user")
	private String gender;
	@ApiModelProperty(notes = "The password of the user")
	private String password;
    @Indexed(unique=true) //Enable Indexing To Insert Unique Fields
    @ApiModelProperty(notes = "The unique email of the user")
	private String email;
    @Indexed(unique=true) // Enable Indexing To Insert Unique Fields
    @ApiModelProperty(notes = "The unique  mobilenumber of the user")
	private long mobilenumber;
    @ApiModelProperty(notes = "The town of the user")
	private String town;
    @ApiModelProperty(notes = "The area of the user")
	private String area;
    @ApiModelProperty(notes = "The role of the user")
	private String role;
    @ApiModelProperty(notes="The status of the user")
    private String status;
    
    
	//All Argument Constructor
   
    public Users(long id, String name, String gender, String password, String email, long mobilenumber, String town,
			String area, String role, String status) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.mobilenumber = mobilenumber;
		this.town = town;
		this.area = area;
		this.role = role;
		this.status = status;
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


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
