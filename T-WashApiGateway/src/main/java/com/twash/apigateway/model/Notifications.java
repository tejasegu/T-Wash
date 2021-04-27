package com.twash.apigateway.model;

import java.time.LocalDate;






public class Notifications {

	
	private String id;
	private String userid;
	private String description;
	private LocalDate createdDateTime;
	
	
	public Notifications(String id, String userid, String description, LocalDate createdDateTime) {
		super();
		this.id = id;
		this.userid = userid;
		this.description = description;
		this.createdDateTime = createdDateTime;
	}


	public Notifications() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getCreatedDateTime() {
		return createdDateTime;
	}


	public void setCreatedDateTime(LocalDate createdDateTime) {
		this.createdDateTime = createdDateTime;
	}


	
	
	
}
