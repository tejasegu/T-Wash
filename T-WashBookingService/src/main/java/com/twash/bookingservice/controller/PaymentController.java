package com.twash.bookingservice.controller;

import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.*;
import com.twash.bookingservice.config.PaymentConfig;
import com.twash.bookingservice.config.SMSConfig;
import com.twash.bookingservice.model.Bookings;
import com.twash.bookingservice.model.Notifications;
import com.twash.bookingservice.repository.BookingsRepository;
import com.twash.bookingservice.service.BookingsDaoImpl;


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

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@PostMapping(value = "/booking/online")
	public ModelAndView getRedirect(@Validated @RequestBody Bookings booking) throws Exception {
 			Bookings booked=bookingdaoimpl.addBooking(booking);
 			String transactionAmount=booked.getAmount()+"";
 			String orderId=booked.getId()+"";
 			String customerId=booked.getUserid()+"";
 			
 			ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
 			TreeMap<String, String> parameters = new TreeMap<>();
 			paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
 			parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
 			parameters.put("EMAIL", env.getProperty("paytm.email"));
 			parameters.put("ORDER_ID", orderId);
 			parameters.put("TXN_AMOUNT", transactionAmount);
 			parameters.put("CUST_ID", customerId);
 			String checkSum = PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
 			parameters.put("CHECKSUMHASH", checkSum);
 			modelAndView.addAllObjects(parameters);
 			return modelAndView;
 			
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
				} else {
					Bookings	booked=bookingrepo.findById(orderid).get();
					booked.setPaymentstatus("Fail");
					bookingrepo.save(booked);
					result = "Payment Failed";
				}
			} else {
				result = "Checksum mismatched";
			}
		} catch (Exception e) {
			result = e.toString();
		}
		model.addAttribute("result", result);
		parameters.remove("CHECKSUMHASH");
		model.addAttribute("parameters", parameters);
		return "report";
	}


}
