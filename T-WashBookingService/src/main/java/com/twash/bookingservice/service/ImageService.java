package com.twash.bookingservice.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twash.bookingservice.model.Image;
import com.twash.bookingservice.repository.ImageRepository;
@Service
public class ImageService {
	@Autowired
    private ImageRepository imagerepo;
	
	 public String addPhoto(String title, MultipartFile file) { 
	        Image photo = new Image(); 
	        photo.setTitle(title);
	        try {
				photo.setImage(  new Binary(BsonBinarySubType.BINARY, file.getBytes()));
				photo = imagerepo.insert(photo); 
		        return "image uploaded"; 
			} catch (IOException e) {
				return e.getMessage();
			} 
	        
	    }
	 
	 public Image getPhoto(String title) { 
	        return imagerepo.findByTitle(title).get(); 
	    }
}
