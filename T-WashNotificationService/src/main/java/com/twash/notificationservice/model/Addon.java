package com.twash.notificationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="Addon")
public class Addon {
	
	@Id // Generates A Unique Id In MongoDB
	@ApiModelProperty(notes = "The database generated ID")
	private String id;
	@ApiModelProperty(notes = "The name of the Addon")
	private String addonname;
	@ApiModelProperty(notes = "The price of the Addon")
	private String price;
	
	
	
	public Addon(String id, String addonname, String price) {
		super();
		this.id = id;
		this.addonname = addonname;
		this.price = price;
	}
	public Addon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddonname() {
		return addonname;
	}
	public void setAddonname(String addonname) {
		this.addonname = addonname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
