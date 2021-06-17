package com.rentaride;

public class ReservationRecords {
	
	protected String reservationId;
	protected String customerName;
	protected String phoneNum;
	protected String emailId;
	protected String licenseNumber;
	protected String custUserName;
	protected String pickUpLocation;
	protected String pickUpDateTime;
	protected String dropOffDateTime;
	protected String vehRegNo;
	protected String vehTypeId;
	protected String vehType;
	protected String vehMake;
	protected String feesPayable;
	protected String comments;
	
	public String getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustUserName(String custUserName) {
		this.custUserName = custUserName;
	}
	
	public String getCustUserName() {
		return custUserName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	
	public String getPickUpDateTime() {
		return pickUpDateTime;
	}
	
	public void setPickUpDateTime(String pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}
	
	public String getDropOffDateTime() {
		return dropOffDateTime;
	}
	
	public void setDropOffDateTime(String dropOffDateTime) {
		this.dropOffDateTime = dropOffDateTime;
	}
	
	public String getVehRegNo() {
		return vehRegNo;
	}
	
	public void setVehRegNo(String vehRegNo) {
		this.vehRegNo = vehRegNo;
	}
	
	public String getVehTypeId() {
		return vehTypeId;
	}
	
	public void setVehTypeId(String vehTypeId) {
		this.vehTypeId = vehTypeId;
	}
	
	public String getVehType() {
		return vehType;
	}
	
	public void setVehType(String vehType) {
		this.vehType = vehType;
	}
	
	public String getVehMake() {
		return vehMake;
	}
	
	public void setVehMake(String vehMake) {
		this.vehMake = vehMake;
	}
	
	public String getFeesPayable() {
		return feesPayable;
	}
	
	public void setFeesPayable(String feesPayable) {
		this.feesPayable = feesPayable;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

}
