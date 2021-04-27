package com.twash.bookingservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twash.bookingservice.config.SMSConfig;
import com.twash.bookingservice.model.Bookings;
import com.twash.bookingservice.model.Notifications;
import com.twash.bookingservice.repository.BookingsDaoInterface;
import com.twash.bookingservice.repository.BookingsRepository;


@Service
public class BookingsDaoImpl implements BookingsDaoInterface {

	@Autowired
	private BookingsRepository bookingrepo;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private SMSConfig sms;
	@Autowired
	private RabbitTemplate template;
	
	
	@Override
	public Bookings addBooking(Bookings booking) {
		booking.setId(sequenceGeneratorService.generateSequence(Bookings.SEQUENCE_NAME));
		Bookings booked=bookingrepo.save(booking);
		long washernumber=booking.getWashernumber();
		Notifications notify=new Notifications();
		notify.setUserid(booked.getWasherid());
		notify.setDescription("You have a new order with order id ::"+booked.getId());
		notify.setUserid(booked.getUserid());
		notify.setDescription("You have a new order with order id ::"+booked.getId());

		template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
		try {
			sms.sendSMS(washernumber, "Hi, you have a new order");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return booked;
		
	}

	@Override
	public List<Bookings> getAllBookings() {
		
		return bookingrepo.findAll();
	}

	@Override
	public Optional<Bookings> getBookingsbyId(long Id) {
		
		return bookingrepo.findById(Id);
	}

	@Override
	public List<Bookings> getBookingsByUserId(String userid) {
		Optional<List<Bookings>> book=bookingrepo.findByUserid(userid);
    	List<Bookings>totalbookings=book.get().stream().filter(o->!(o.isScheduledlater()) || ( (o.isScheduledlater()) &&( ("Rejected".equalsIgnoreCase(o.getOrderstatus()) || ("Completed".equalsIgnoreCase(o.getOrderstatus())))))).collect(Collectors.toList());
		return totalbookings;
	}
	@Override
	public List<Bookings> getBookingsByWasherId(String washerid) {
		Optional<List<Bookings>> book=bookingrepo.findByWasherid(washerid);
    	List<Bookings>totalbookings=book.get().stream().filter(o->!(o.isScheduledlater()) || ( (o.isScheduledlater()) &&( ("Rejected".equalsIgnoreCase(o.getOrderstatus()) || ("Completed".equalsIgnoreCase(o.getOrderstatus())))))).collect(Collectors.toList());
		return totalbookings;
	}


	@Override
	public void updateBookingStatusbyId(long Id,String status) {
		Optional<Bookings> booking=bookingrepo.findById(Id);
		Bookings bookingtosave=booking.get();
		long number=bookingtosave.getNumber();
		
		Notifications notify=new Notifications();
		notify.setUserid(bookingtosave.getUserid());
		long orderid=bookingtosave.getId();
		if("Accepted".equals(status)) {
			
			bookingtosave.setOrderstatus(status);
			bookingrepo.save(bookingtosave);
			notify.setDescription("Hi, Your order has been accepted by the washer and will be proceesed soon with order id ::"+orderid);
			template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
			     
			try {
				
				sms.sendSMS(number, "Hi, Your order has been accepted by the washer and will be processed soon");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("Rejected".equals(status)){
			
			notify.setDescription("Sorry, Your order has been rejected by the washer with order id ::"+orderid+". Please start a new booking.");
			template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
			bookingtosave.setOrderstatus(status);
			bookingrepo.save(bookingtosave);
			try {
				sms.sendSMS(number, "Sorry, Your order has been rejected by the washer. Please start a new booking. ");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("In progress".equals(status)){
	
			notify.setDescription("Hi, Your car washing is started by the washer with order id ::"+orderid+". We will inform you once the wash finishes.");
			template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
			bookingtosave.setOrderstatus(status);
			bookingrepo.save(bookingtosave);
			try {
				sms.sendSMS(number, "Hi, Your car washing is started by the washer we will inform you once the wash finishes.");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("Completed".equals(status)){
			
			notify.setDescription("Hi, Your wash has been completed  with order id ::"+orderid+". Thank you for booking a wash. Have a nice day!");
			template.convertAndSend("notificationexchange", "notificationroutingkey", notify);
			bookingtosave.setOrderstatus(status);
			bookingrepo.save(bookingtosave);
			try {
				sms.sendSMS(number, "Hi, Your wash has been completed and thank you for booking a wash. Have a nice day");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void updatePaymentStatusbyId(long Id,String paymentstatus) {
		Optional<Bookings> booking=bookingrepo.findById(Id);
		Bookings bookingtosave=booking.get();
		bookingtosave.setPaymentstatus(paymentstatus);
		bookingrepo.save(bookingtosave);
	}

	@Override
	public void deleteBookingsbyId(long Id) {
		bookingrepo.deleteById(Id);
	}

	@Override
	public long getTotalIncome() {
		List<Bookings> allbookings=bookingrepo.findAll();
		long income=allbookings.stream().filter(o->"Completed".equals(o.getOrderstatus()) && "Success".equals(o.getPaymentstatus())).mapToLong(o->o.getAmount()).sum();
		
		return income;
	}
	

	@Override
    public List<Bookings> getScheduledBookingsByUserId(String userid) {
    	Optional<List<Bookings>> book=bookingrepo.findByUserid(userid);
    	List<Bookings>scheduled=book.get().stream().filter(o->o.isScheduledlater() && !( ("Completed".equalsIgnoreCase(o.getOrderstatus()) || ("Rejected".equalsIgnoreCase(o.getOrderstatus()))))).collect(Collectors.toList());
		return scheduled ;
	}

	@Override
	public List<Bookings> getScheduledBookingsByWasherId(String washerid) {
		Optional<List<Bookings>> book= bookingrepo.findByWasherid(washerid);
    	List<Bookings>scheduled=book.get().stream().filter(o->o.isScheduledlater()&& !("Completed".equalsIgnoreCase(o.getOrderstatus())) &&  !("Rejected".equalsIgnoreCase(o.getOrderstatus()))).collect(Collectors.toList());

		return scheduled;
	}

}
