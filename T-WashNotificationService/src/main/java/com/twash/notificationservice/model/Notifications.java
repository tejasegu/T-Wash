package com.twash.notificationservice.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="Notifications")

/**
 * The Users Class Is A Model Class For The Notifications 
 * Schema Which Is To Be Present In Database
 */
public class Notifications {

	@Id // Generates A Unique Id In MongoDB
	@ApiModelProperty(notes = "The database generated ID")
	private String id;
	@ApiModelProperty(notes = "The id of the user/washer")
	private String userid;
	@ApiModelProperty(notes = "The description of the notification")
	private String description;
	@Indexed(name = "createdDateTime", expireAfterSeconds = 604800)
	@ApiModelProperty(notes = "The date of the notification created")
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
