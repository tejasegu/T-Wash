package com.twash.apigateway.model;




public class Addon {
	
	
	private String id;
	private String addonname;
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
