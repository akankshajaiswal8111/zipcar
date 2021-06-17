package com.rentaride;

import static java.time.temporal.ChronoUnit.HOURS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDAO {
	
	 private Connection conn;
	 
	 public ReservationDAO() throws ClassNotFoundException, SQLException  {
	    	conn = DatabaseManager.initializeDatabase();
	}
	 
	 
	 public ReservationRecords getReservationDetails(ReservationRecords reservationRecords) {
	        try {
	        	String sql = "SELECT Reservation.Customer, rentlocations.Name, Reservation.PickUpDateTime, Reservation.DropOffDateTime, vehicles.RegNo, Reservation.FeesPayable " + 
						  "FROM reservation INNER JOIN vehicles ON " + 
						  "reservation.Vehicle=vehicles.RegNo " + 
						  "INNER JOIN RentLocations ON " + 
						  "vehicles.Location=RentLocations.RentLocationId " + 
						  "WHERE Reservation.ReservationId=?";
	        	PreparedStatement ps = conn.prepareStatement(sql);
	        	ps.setString(1, reservationRecords.getReservationId());
	        	ResultSet rs = ps.executeQuery();
	        	
	        	while (rs.next()) {
	        		reservationRecords.setCustomerName(rs.getString("Reservation.Customer")); 
	        		reservationRecords.setPickUpLocation(rs.getString("rentlocations.Name")); 
	        		reservationRecords.setPickUpDateTime(rs.getString("Reservation.PickUpDateTime")); 
	        		reservationRecords.setDropOffDateTime(rs.getString("Reservation.DropOffDateTime"));
	        		reservationRecords.setVehRegNo(rs.getString("vehicles.RegNo"));
	        		reservationRecords.setFeesPayable(rs.getString("Reservation.FeesPayable"));
	        	}

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return reservationRecords;
	    }
	 
	 public ReservationRecords getReservationDetailsByVehicle(String vehRegNo)
	 {
		 ReservationRecords reservationRecords = new ReservationRecords();
		 try
		 {
			 String sql = "SELECT RentLocations.Name, vehicles.RegNo, vehicles.Make, vehicleType.Type, vehicleType.PricePerHr, vehicleType.vehicleTypeId"
						+ " FROM vehicles INNER JOIN vehicleType ON"
						+ " vehicles.Type=vehicleType.vehicleTypeId"
						+ " INNER JOIN RentLocations ON vehicles.Location=RentLocations.RentLocationId"
						+ " WHERE vehicles.RegNo=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, vehRegNo);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					reservationRecords.setPickUpLocation(rs.getString("RentLocations.Name"));
					reservationRecords.setVehType(rs.getString("vehicleType.Type"));   
					reservationRecords.setFeesPayable(rs.getString("vehicleType.PricePerHr"));;
					reservationRecords.setVehRegNo(rs.getString("vehicles.RegNo"));;
					reservationRecords.setVehMake(rs.getString("vehicles.Make"));;	
					reservationRecords.setVehTypeId(rs.getString("vehicleType.vehicleTypeId"));;
				}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return reservationRecords;
	 }
	 
	 public void addReservationDetails(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 String sql = "insert into reservation (Customer, Vehicle, VehicleType, PickUpDateTime, DropOffDateTime, booked, FeesPayable) "+"values (?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, reservationRecords.getCustUserName());
				ps.setString(2, reservationRecords.getVehRegNo());
				ps.setString(3, reservationRecords.getVehTypeId());
				ps.setString(4, reservationRecords.getPickUpDateTime());
				ps.setString(5, reservationRecords.getDropOffDateTime());
				ps.setBoolean(6, true);
				ps.setString(7, reservationRecords.getFeesPayable());
				ps.executeUpdate();
				ps.close();
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
	 }
	 
	 public ReservationRecords getReservationByVehReg(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 String sql = "SELECT Reservation.Customer, rentlocations.Name, Reservation.PickUpDateTime, Reservation.DropOffDateTime, vehicles.RegNo, Reservation.FeesPayable " + 
					  "FROM reservation INNER JOIN vehicles ON " + 
					  "reservation.Vehicle=vehicles.RegNo " + 
					  "INNER JOIN RentLocations ON " + 
					  "vehicles.Location=RentLocations.RentLocationId " + 
					  "WHERE Reservation.Customer=? and reservation.Vehicle=? and booked=true";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, reservationRecords.getCustUserName());
			ps.setString(2, reservationRecords.getVehRegNo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				reservationRecords.setCustomerName(rs.getString("Reservation.Customer"));
				reservationRecords.setPickUpLocation(rs.getString("rentlocations.Name"));
				reservationRecords.setPickUpDateTime(rs.getString("Reservation.PickUpDateTime"));
				reservationRecords.setDropOffDateTime(rs.getString("Reservation.DropOffDateTime"));
				reservationRecords.setVehRegNo(rs.getString("vehicles.RegNo"));
				reservationRecords.setFeesPayable(rs.getString("Reservation.FeesPayable"));
			}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return reservationRecords;
	 }
	 
	 public String getRentalFeesPerHr(ReservationRecords reservationRecords)
	 {
		 String pricePerHr = null;
		 try
		 {
			 String sql = "SELECT vehicleType.PricePerHr"
						+ " FROM reservation INNER JOIN vehicleType ON"
						+ " Reservation.VehicleType = vehicleType.VehicleTypeId"
						+ " WHERE Reservation.Customer=? and reservation.Vehicle=? and booked = 1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, reservationRecords.getCustUserName());
				ps.setString(2, reservationRecords.getVehRegNo());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) 
				{
					pricePerHr = rs.getString("vehicleType.PricePerHr");
				}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return pricePerHr;
	 }
	 
	 public String getFeesperHrOnVehicle(ReservationRecords reservationRecords)
	 {
		 String fees = null;
		 try
		 {
			 	String sql = "Select priceperhr, vehicletypeid from vehicletype where vehicletypeid = (select type from vehicles where regno = ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, reservationRecords.getVehRegNo());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					fees = rs.getString("priceperhr");
				}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return fees;
	 }
	 
	 public void cancelReservationWithFees(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 String sql = "update reservation set booked=false, FeesPayable=?, comments=? where Customer=? and Vehicle=? and booked=1";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, reservationRecords.getFeesPayable());
				st.setString(2, reservationRecords.getComments());
				st.setString(3, reservationRecords.getCustUserName());
				st.setString(4, reservationRecords.getVehRegNo());
				st.executeUpdate();
				st.close();
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public void cancelReservation(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 	String sql = "update reservation set booked=false, feesPayable=0, comments=? where Customer=? and Vehicle=? and booked=1";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, reservationRecords.getComments());
				st.setString(2, reservationRecords.getCustUserName());
				st.setString(3, reservationRecords.getVehRegNo());
				st.executeUpdate();
				st.close();
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public void endReservationWithFees(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 	String sql = "update reservation set booked=false, FeesPayable=?, comments=? where Customer=? and Vehicle=? and booked=1";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, reservationRecords.getFeesPayable());
				st.setString(2, reservationRecords.getComments());
				st.setString(3, reservationRecords.getCustUserName());
				st.setString(4, reservationRecords.getVehRegNo());
				st.executeUpdate();
				st.close();
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public void endReservation(ReservationRecords reservationRecords)
	 {
		 try
		 {
			 	String sql = "update reservation set booked=false, comments=? where Customer=? and Vehicle=? and booked=1";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, reservationRecords.getComments());
				st.setString(2, reservationRecords.getCustUserName());
				st.setString(3, reservationRecords.getVehRegNo());
				st.executeUpdate();
				st.close();
				
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public String getLateFees()
	 {
		 String lateFees = null;
		 try
		 {
			 	String sql = "select Amount from fees where FeeType='Late Return Fee'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) 
				{
					lateFees = rs.getString("Amount");
				}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return lateFees;
	 }
	 
	 public ReservationRecords getCustomerDetails(String userName)
	 {
		 ReservationRecords reservationRecords = new ReservationRecords();
		 try
		 {
				String sql = "select Name, EmailId, LicenseNumber, PhoneNum from customerdetails where Username=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, userName);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					reservationRecords.setCustomerName(rs.getString("Name"));
					reservationRecords.setPhoneNum(rs.getString("PhoneNum"));
					reservationRecords.setEmailId(rs.getString("EmailId"));
					reservationRecords.setLicenseNumber(rs.getString("LicenseNumber"));
				}
		 }
		 catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return reservationRecords;
	 }
	 

}
