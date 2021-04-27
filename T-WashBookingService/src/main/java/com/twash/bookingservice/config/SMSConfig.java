package com.twash.bookingservice.config;


import org.springframework.context.annotation.Configuration;
import com.twilio.rest.api.v2010.account.Message;


import com.twilio.Twilio;

@Configuration
public class SMSConfig {

	 public static final String ACCOUNT_SID = "ACdc604bc92c0ac780b2ae7d4a9b2e8326"; 
	    public static final String AUTH_TOKEN = "d826759b5e5228798c0fdbc5cc690b03"; 

	public void sendSMS(long number, String message)  throws Exception {
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message messages = Message.creator(new com.twilio.type.PhoneNumber("+91"+number),  
	                "MGf713f42677df84cd9d3c03c70e5cb336", 
			        message).create();
		 System.out.println(messages.getSid());
	}
}
