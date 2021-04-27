package com.twash.apigateway.model;



public class Users {


	private long id;
	private String name;
	private String gender;
	private String password;
	private String email;
	private long mobilenumber;
	private String town;
	private String area;
	private String role;
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
