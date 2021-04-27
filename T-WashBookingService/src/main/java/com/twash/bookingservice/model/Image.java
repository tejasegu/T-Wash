package com.twash.bookingservice.model;


import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="Image")
public class Image {
	
	//Stores image as bytes
	@ApiModelProperty(notes = "The image")
	private Binary image;
	
	//Stores order id
	@ApiModelProperty(notes = "The title which is order id of image")
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
