package com.twash.apigateway.model;


import java.util.Date;




public class Bookings {
	
	private long id;
	private Date bookedon;
	private boolean scheduledlater;
	private Date scheduleddate;
	private String userid;
	private String username;
	private long number;
	private String carname;
	private String carnumber;
	private String area;
	private String location;
	private String washerid;
	private String washername;
	private long washernumber;
	private String packages;
	private String addon;
	private String orderstatus;
	private String paymenttype;
	private String transactionid;
	private String paymentstatus;
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
