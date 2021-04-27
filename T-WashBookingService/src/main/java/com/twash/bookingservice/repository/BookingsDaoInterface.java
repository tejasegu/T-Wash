package com.twash.bookingservice.repository;

import java.util.List;
import java.util.Optional;

import com.twash.bookingservice.model.Bookings;



public interface BookingsDaoInterface {
	public Bookings addBooking(Bookings booking);
	public List<Bookings> getAllBookings();
	public Optional<Bookings> getBookingsbyId(long Id);
	public List<Bookings> getBookingsByUserId(String userid);
	public List<Bookings> getBookingsByWasherId(String washerid);
	public void updateBookingStatusbyId(long Id, String status);
	public void deleteBookingsbyId(long Id);
	public long getTotalIncome();
	void updatePaymentStatusbyId(long Id, String paymentstatus);
	 public List<Bookings> getScheduledBookingsByUserId(String userid) ;
	 public List<Bookings> getScheduledBookingsByWasherId(String washerid) ;
}
