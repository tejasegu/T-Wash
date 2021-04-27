package com.twash.bookingservice.config;


import org.springframework.context.annotation.Configuration;
import com.twilio.rest.api.v2010.account.Message;


import com.twilio.Twilio;

@Configuration
public class SMSConfig {

	 public static final String ACCOUNT_SID = "xxxxxxxxxxx"; 
	    public static final String AUTH_TOKEN = "xxxxxxxxxx"; 

	public void sendSMS(long number, String message)  throws Exception {
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message messages = Message.creator(new com.twilio.type.PhoneNumber("+91"+number),  
	                "xxxxxx", 
			        message).create();
		 System.out.println(messages.getSid());
	}
}
