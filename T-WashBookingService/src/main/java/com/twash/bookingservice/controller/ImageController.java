package com.twash.bookingservice.controller;


import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.twash.bookingservice.model.Image;
import com.twash.bookingservice.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class ImageController {

	@Autowired
	private ImageService imageservice;
	
	 //To Insert Image Into DataBase
 	/**
 	 * @param title
 	 * @param image
 	 * @return
 	 */
 	@Operation(summary = "Adds a new image")
	@PostMapping("/images/{title}/add")
	public ResponseEntity<String> addPhoto(@PathVariable("title") String title, 
	  @RequestParam("image") MultipartFile image) {
	    String id = imageservice.addPhoto(title, image);
	    return  ResponseEntity.ok(id);
		
	}
	
 	 //To Get Image From DataBase
 	@Operation(summary = "Get the image ")
	@GetMapping("/images/{title}")
	public String getPhoto(@PathVariable String title) {
	   Image photo = imageservice.getPhoto(title);
	   Encoder encoder=Base64.getEncoder();
	   
	    return encoder.encodeToString(photo.getImage().getData()).trim(); 
	}
 	
 

}
