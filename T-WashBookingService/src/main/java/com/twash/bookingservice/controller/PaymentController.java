package com.twash.bookingservice.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.paytm.pg.merchant.*;
import com.twash.bookingservice.config.PaymentConfig;
import com.twash.bookingservice.config.SMSConfig;
import com.twash.bookingservice.model.Bookings;
import com.twash.bookingservice.model.Notifications;
import com.twash.bookingservice.repository.BookingsRepository;
import com.twash.bookingservice.service.BookingsDaoImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class PaymentController {
	@Autowired
	private PaymentConfig paytmDetails;
	@Autowired
	private Environment env;
	 @Autowired
	   private BookingsDaoImpl bookingdaoimpl;
	 @Autowired
		private BookingsRepository bookingrepo;
	 @Autowired
		private SMSConfig sms;
	 
	 @Autowired
		private RabbitTemplate template;


	@PostMapping(value = "/booking/online")
	@ResponseBody
	public String getRedirect(@Validated @RequestBody Bookings booking) throws Exception {
 			Bookings booked=bookingdaoimpl.addBooking(booking);
 			String transactionAmount=booked.getAmount()+"";
 			String orderId=booked.getId()+"";
 			String customerId=booked.getUserid()+"";
 			
 			//ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
 			TreeMap<String, String> parameters = new TreeMap<>();
 			paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
 			parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
 			parameters.put("EMAIL", env.getProperty("paytm.email"));
 			parameters.put("ORDER_ID", orderId);
 			parameters.put("TXN_AMOUNT", transactionAmount);
 			parameters.put("CUST_ID", customerId);
 			String checkSum = PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
 			//parameters.put("CHECKSUMHASH", checkSum);
 			//modelAndView.addAllObjects(parameters);
 			//return modelAndView;
			
			
			
			  String url = paytmDetails.getPaytmUrl(); 
				/*
				 * UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(url)
				 * .queryParam("CALLBACK_URL", URLEncoder.encode(paytmDetails.getCallbackUrl(),
				 * StandardCharsets.UTF_8.toString()) ) .queryParam("CHANNEL_ID",
				 * paytmDetails.getChannelId()) .queryParam("CHECKSUMHASH",
				 * UriEncoder.encode(checkSum)) .queryParam("CUST_ID",customerId)
				 * .queryParam("EMAIL", UriEncoder.encode(env.getProperty("paytm.email")) )
				 * .queryParam("INDUSTRY_TYPE_ID",paytmDetails.getIndustryTypeId())
				 * .queryParam("MID", paytmDetails.getMerchantId())
				 * .queryParam("MOBILE_NO",env.getProperty("paytm.mobile"))
				 * .queryParam("ORDER_ID", orderId) .queryParam("TXN_AMOUNT", transactionAmount)
				 * .queryParam("WEBSITE", paytmDetails.getWebsite());
				 */
			  
			  StringBuilder builder = new StringBuilder(url);
			
			          builder.append("?CALLBACK_URL=");
			  
			  builder.append(URLEncoder.encode(paytmDetails.getCallbackUrl(),StandardCharsets.UTF_8.toString()));
			  
			          builder.append("&CHANNEL_ID=");
			  
			          builder.append(URLEncoder.encode(paytmDetails.getChannelId(),StandardCharsets.UTF_8.toString()));
			  
			          builder.append("&CHECKSUMHASH=");
			  
			          builder.append(URLEncoder.encode(checkSum,StandardCharsets.UTF_8.toString()));
			          builder.append("&CUST_ID=");
					  
			          builder.append(URLEncoder.encode(customerId,StandardCharsets.UTF_8.toString()));
			          builder.append("&EMAIL=");
					  
			          builder.append(URLEncoder.encode(env.getProperty("paytm.email"),StandardCharsets.UTF_8.toString()));
			          builder.append("&INDUSTRY_TYPE_ID=");
					  
			          builder.append(URLEncoder.encode(paytmDetails.getIndustryTypeId(),StandardCharsets.UTF_8.toString()));
			          builder.append("&MID=");
					  
			          builder.append(URLEncoder.encode(paytmDetails.getMerchantId(),StandardCharsets.UTF_8.toString()));
                      builder.append("&MOBILE_NO=");
					  
			          builder.append(URLEncoder.encode(env.getProperty("paytm.mobile"),StandardCharsets.UTF_8.toString()));
 builder.append("&ORDER_ID=");
					  
			          builder.append(URLEncoder.encode(orderId,StandardCharsets.UTF_8.toString()));
 builder.append("&TXN_AMOUNT=");
					  
			          builder.append(URLEncoder.encode(transactionAmount,StandardCharsets.UTF_8.toString()));
 builder.append("&WEBSITE=");
					  
			          builder.append(URLEncoder.encode(paytmDetails.getWebsite(),StandardCharsets.UTF_8.toString()));
			  
			          
			  
			      URI uri = URI.create(builder.toString());
			  return uri.toString();
			 

 			
	}

	@PostMapping(value = "/bookings/response")
	public String getResponseRedirect(HttpServletRequest request, Model model) {
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum="";
		for (Entry<String, String[]> requestParamsEntry : request.getParameterMap().entrySet()) {
		    if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
		        paytmChecksum = requestParamsEntry.getValue()[0];
		    } else {
		        parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
		    }
		}
		String result;
		System.out.println("RESULT : " + parameters.toString());
		long orderid=Long.parseLong(parameters.get("ORDERID"));
		System.out.println(orderid);
		try {
			boolean isVerifySignature;
			isVerifySignature = PaytmChecksum.verifySignature(parameters, paytmDetails.getMerchantKey(), paytmChecksum);
			if (isVerifySignature && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
				Bookings	booked=bookingrepo.findById(orderid).get();
				booked.setPaymentstatus("Sucess");
				bookingrepo.save(booked);
				long washernumber=booked.getWashernumber();
				Notifications notify=new Notifications();
				//notify.setCreatedDateTime(LocalDate.now());
				notify.setUserid(booked.getWasherid());
				notify.setDescription("You have a new order with order id ::"+booked.getId());
				template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
				sms.sendSMS(washernumber, "Hi, you have a new order");
				
					result = "Payment Successful";
					//parameters.remove("CHECKSUMHASH");
					//return result;
				} else {
					Bookings	booked=bookingrepo.findById(orderid).get();
					booked.setPaymentstatus("Fail");
					bookingrepo.save(booked);
					result = "Payment Failed";
					//parameters.remove("CHECKSUMHASH");

					//return result;
				}
			} else {
				result = "Checksum mismatched";
				//parameters.remove("CHECKSUMHASH");

			}
		} catch (Exception e) {
			result = e.toString();
			//parameters.remove("CHECKSUMHASH");

		}
		model.addAttribute("result", result);
		
		model.addAttribute("parameters", parameters);
		parameters.remove("CHECKSUMHASH");
		return "report";
	}


}
