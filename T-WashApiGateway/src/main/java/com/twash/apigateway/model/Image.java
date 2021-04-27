package com.twash.apigateway.model;


import org.bson.types.Binary;



public class Image {
	
	
	private Binary image;
    private String title;
	
	//No Argument Constructor
	public Image() {
	}
	
	// Getters And Setters
	public Binary getImage() {
		return image;
	}
	public void setImage( Binary image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
