package com.twash.userservice.exception;

public class ErrorDetails {
	 private String message;
	    public ErrorDetails( String message) {
	        super();
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }
	}