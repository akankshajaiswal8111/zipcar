package com.rentaride;

import java.sql.Date;
import java.time.*;
//import java.util.Date;

public class VehicleMgr {
	protected String regno;
	protected String type;
	protected String location;
	protected String make;
	protected int modelno;
	protected double mileage;
	protected LocalDate lastservicedate;
	protected int year;
	protected String quality;

	
	public String getRegNo() {
        return regno;
    }
 
    public void setRegNo(String regno) {
        this.regno = regno;
    }
    
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public int getModelNo() {
		return modelno;
	}
	
	public void setModelNo(int modelno) {
		this.modelno = modelno;
	}
	
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	
	public LocalDate getLastServiceDate() {
		return lastservicedate;
	}
	
	public void setLastServiceDate(LocalDate lastservicedate) {
		this.lastservicedate = lastservicedate;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getQuality() {
		return quality;
	}
	
	public void setQuality(String quality) {
		this.quality = quality;
	}

}


