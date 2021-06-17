package com.rentaride;
public class RentLocation {
	protected int id;
	protected String name;
	protected String address;
	protected int vehicleCapacity;
	protected int availableCapacity;
	
//	public RentLocation() {
//	}
//	
//	public RentLocation(int id) {
//		this.id = id;
//	}
//	public RentLocation(int id, String name, String address, int vehicleCapacity) {
//		this.id = id;
//		this.name =name;
//        this.address = address;
//        this.vehicleCapacity = vehicleCapacity;
//    }
//	
////	public RentLocation(String name, String address, int vehicleCapacity) {
////		this.address = address;
////		this.vehicleCapacity = vehicleCapacity;
////		this.name = name;
////	}
	
	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getVehicleCapacity() {
		return vehicleCapacity;
	}
	
	public void setVehicleCapacity(int vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}
	
	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}
}

