package com.twash.userservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
@Document(collection="Reviews")
public class Reviews {
	
	@ApiModelProperty(notes = "The database generated review ID")
	@Id // Generates A Unique Id In MongoDB
	private String id;
	@ApiModelProperty(notes = "The name of the user")
	private String username;
	@ApiModelProperty(notes = "The id of the user")
	private String userid;
	@ApiModelProperty(notes = "The id of the user")
	private String washerid;
	@ApiModelProperty(notes = "The review")
	private String review;
	@ApiModelProperty(notes = "The rating to washer")
	private long rating;
	public Reviews(String id, String username, String userid, String washerid, String review, long rating) {
		super();
		this.id = id;
		this.username = username;
		this.userid = userid;
		this.washerid = washerid;
		this.review = review;
		this.rating = rating;
	}
	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWasherid() {
		return washerid;
	}
	public void setWasherid(String washerid) {
		this.washerid = washerid;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
   
}
