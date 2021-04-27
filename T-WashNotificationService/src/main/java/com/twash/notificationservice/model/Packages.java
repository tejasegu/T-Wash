package com.twash.notificationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="Packages")
public class Packages {
	
	@Id // Generates A Unique Id In MongoDB
	@ApiModelProperty(notes = "The database generated ID")
	private String id;
	@ApiModelProperty(notes = "The name of the package")
	private String packagename;
	@ApiModelProperty(notes = "The price of the model type HBack")
	private String hback;
	@ApiModelProperty(notes = "The price of the model type Sedan")
	private String sedan;
	@ApiModelProperty(notes = "The price of the model type Suv")
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
