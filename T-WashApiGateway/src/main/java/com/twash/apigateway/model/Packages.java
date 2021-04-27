package com.twash.apigateway.model;






public class Packages {
	

	private String id;
	private String packagename;
	private String hback;
	private String sedan;
	private String suv;
	
	// All argument constructor
	public Packages(String id, String packagename, String hback, String sedan, String suv) {
		super();
		this.id = id;
		this.packagename = packagename;
		this.hback = hback;
		this.sedan = sedan;
		this.suv = suv;
	}
	
	//No argument constructor
	public Packages() {
		super();
		
	}

	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getHback() {
		return hback;
	}

	public void setHback(String hback) {
		this.hback = hback;
	}

	public String getSedan() {
		return sedan;
	}

	public void setSedan(String sedan) {
		this.sedan = sedan;
	}

	public String getSuv() {
		return suv;
	}

	public void setSuv(String suv) {
		this.suv = suv;
	}
	
	
}
