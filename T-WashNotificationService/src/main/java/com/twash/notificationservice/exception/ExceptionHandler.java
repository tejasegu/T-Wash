package com.twash.notificationservice.exception;

public class ExceptionHandler extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
    public String IdNotFoundException(String id) {
		return "Notifications with UserId :: "+id+" is not Found";
	}


}
