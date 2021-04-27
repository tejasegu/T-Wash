package com.twash.bookingservice.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import io.swagger.annotations.ApiModelProperty;

@Document(collection="Bookings")
public class Bookings {
	@Transient //  By Default All Private Fields Are Mapped To The Document,  This Annotation Excludes This Field Where It Is Applied From Being Stored In The Database
    public static final String SEQUENCE_NAME = "bookings_sequence";
	@Id // Generates A Unique Id In MongoDB
	@ApiModelProperty(notes = "The database generated user ID")
	private long id;
	@ApiModelProperty(notes = "The dateandtime of booking")
	private Date bookedon;
	@Indexed
	@ApiModelProperty(notes = "Is booking scheduled later")
	private boolean scheduledlater;
	@ApiModelProperty(notes = "The scheduled later booking dater")
	@DateTimeFormat(iso=ISO.DATE)
	private Date scheduleddate;
	@ApiModelProperty(notes = "The id of the user")
	 @Indexed
	private String userid;
	@ApiModelProperty(notes = "The name of the user")
	private String username;
	@ApiModelProperty(notes = "The mobilenumber of the user")
	private long number;
	@ApiModelProperty(notes = "The name of the car")
	private String carname;
	@ApiModelProperty(notes = "The number of the user")
	private String carnumber;
	@ApiModelProperty(notes = "The Area of the user")
	private String area;
	@ApiModelProperty(notes = "The location of the user")
	private String location;
	@ApiModelProperty(notes = "The id of the washer")
	 @Indexed
	private String washerid;
	@ApiModelProperty(notes = "The name of the washer")
	private String washername;
	@ApiModelProperty(notes = "The number of the washer")
	private long washernumber;
	@ApiModelProperty(notes = "The package selected by user")
	private String packages;
	@ApiModelProperty(notes = "The addon selected by user")
	private String addon;
	@ApiModelProperty(notes = "The order status")
	 @Indexed
	private String orderstatus;
	@ApiModelProperty(notes = "The paymenttype")
	private String paymenttype;
	@ApiModelProperty(notes = "The transaction id")
	private String transactionid;
	@ApiModelProperty(notes = "The paymentstatus")
	 @Indexed
	private String paymentstatus;
	@ApiModelProperty(notes = "Total amount")
	private long amount;
	
	
	
	public Bookings(long id, Date bookedon, boolean scheduledlater, Date scheduleddate, String userid,
			String username, long number, String carname, String carnumber, String area, String location,
			String washerid, String washername, long washernumber, String packages, String addon,
			String orderstatus, String paymenttype, String transactionid, String paymentstatus, long amount) {
		super();
		this.id = id;
		this.bookedon = bookedon;
		this.scheduledlater = scheduledlater;
		this.scheduleddate = scheduleddate;
		this.userid = userid;
		this.username = username;
		this.number = number;
		this.carname = carname;
		this.carnumber = carnumber;
		this.area = area;
		this.location = location;
		this.washerid = washerid;
		this.washername = washername;
		this.washernumber = washernumber;
		this.packages = packages;
		this.addon = addon;
		this.orderstatus = orderstatus;
		this.paymenttype = paymenttype;
		this.transactionid = transactionid;
		this.paymentstatus = paymentstatus;
		this.amount = amount;
	}



	public Bookings() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Date getBookedon() {
		return bookedon;
	}



	public void setBookedon(Date bookedon) {
		this.bookedon = bookedon;
	}



	public boolean isScheduledlater() {
		return scheduledlater;
	}



	public void setScheduledlater(boolean scheduledlater) {
		this.scheduledlater = scheduledlater;
	}



	public Date getScheduleddate() {
		return scheduleddate;
	}



	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public long getNumber() {
		return number;
	}



	public void setNumber(long number) {
		this.number = number;
	}



	public String getCarname() {
		return carname;
	}



	public void setCarname(String carname) {
		this.carname = carname;
	}



	public String getCarnumber() {
		return carnumber;
	}



	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getWasherid() {
		return washerid;
	}



	public void setWasherid(String washerid) {
		this.washerid = washerid;
	}



	public String getWashername() {
		return washername;
	}



	public void setWashername(String washername) {
		this.washername = washername;
	}



	public long getWashernumber() {
		return washernumber;
	}



	public void setWashernumber(long washernumber) {
		this.washernumber = washernumber;
	}



	public String getPackages() {
		return packages;
	}



	public void setPackages(String packages) {
		this.packages = packages;
	}



	public String getAddon() {
		return addon;
	}



	public void setAddon(String addon) {
		this.addon = addon;
	}



	public String getOrderstatus() {
		return orderstatus;
	}



	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}



	public String getPaymenttype() {
		return paymenttype;
	}



	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}



	public String getTransactionid() {
		return transactionid;
	}



	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}



	public String getPaymentstatus() {
		return paymentstatus;
	}



	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}



	public long getAmount() {
		return amount;
	}



	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
}
