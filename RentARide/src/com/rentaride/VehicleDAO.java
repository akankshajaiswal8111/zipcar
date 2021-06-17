package com.rentaride;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.sql.*;

public class VehicleDAO {

    private Connection conn;

    public VehicleDAO() throws ClassNotFoundException, SQLException  {
    	conn = DatabaseManager.initializeDatabase();
    }

    public boolean addVehicle(VehicleMgr vehicle) {
        try {
        	String sql = "INSERT INTO vehicles(regno, type, location, make, modelno, mileage, lastservicedate, year, quality)" +
    		" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, vehicle.getRegNo());
            ps.setString(2, vehicle.getType());
            ps.setString(3, vehicle.getLocation());
            ps.setString(4, vehicle.getMake());
            ps.setInt(5, vehicle.getModelNo());
            ps.setDouble(6, vehicle.getMileage());
            ps.setDate(7, java.sql.Date.valueOf(vehicle.getLastServiceDate()));
            ps.setInt(8, vehicle.getYear());
            ps.setString(9, vehicle.getQuality());
            int numRecords = ps.executeUpdate();
            if(numRecords > 0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeVehicle(String regNo) {
        try {
        	String sql = "DELETE FROM vehicles WHERE RegNo=?";
            PreparedStatement ps = conn
                    .prepareStatement(sql);
            ps.setString(1, regNo);
            int numRecords =  ps.executeUpdate();
            if (numRecords>0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        	//out.println("Vehicles associated");
        }
        return false;
      }

    public boolean editVehicle(VehicleMgr vehicle) throws SQLException{    	
    	try {
    		
    		
    		String sql = "UPDATE vehicles SET type=?, location = ?, make=?, modelno=?, mileage=?,lastservicedate=?, year=?, quality=? WHERE RegNo=?";
    		System.out.println("type.."+vehicle.getType());
    		System.out.println("loc.."+vehicle.getLocation());
    		System.out.println(vehicle.getMake());
    		System.out.println(vehicle.getModelNo());
            PreparedStatement ps = conn
                    .prepareStatement(sql);
            //ps.setString(1, vehicle.getRegNo());
            ps.setString(1, vehicle.getType());
            ps.setString(2, vehicle.getLocation());
            ps.setString(3, vehicle.getMake());
            ps.setInt(4, vehicle.getModelNo());
            ps.setDouble(5, vehicle.getMileage());
            ps.setDate(6, java.sql.Date.valueOf(vehicle.getLastServiceDate()));
            ps.setInt(7, vehicle.getYear());
            ps.setString(8, vehicle.getQuality());
            ps.setString(9, vehicle.getRegNo());
            int numRecords = ps.executeUpdate();  
            if(numRecords > 0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    public List<VehicleMgr> listAllVehicles() throws SQLException {
        List<VehicleMgr> listVehicles = new ArrayList<>();
        try { 
        String sql = "SELECT b.regno, a.type, c.name as location,  b.make, b.modelno, b.mileage, b.lastservicedate, b.year, b.quality FROM rentaride.vehicles b INNER JOIN rentaride.vehicletype a ON a.vehicletypeid = b.type INNER JOIN rentaride.rentlocations c ON c.rentlocationid = b.location";
        PreparedStatement ps = conn
                .prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
         
        while (rs.next()) {
        	VehicleMgr vehicle = new VehicleMgr();
        	vehicle.setRegNo(rs.getString("RegNo"));
        	vehicle.setType(rs.getString("Type"));
            vehicle.setLocation(rs.getString("Location")); 
            vehicle.setMake(rs.getString("Make"));
            vehicle.setModelNo(rs.getInt("ModelNo"));
            vehicle.setMileage(rs.getDouble("Mileage"));
            vehicle.setLastServiceDate(rs.getDate("LastServiceDate").toLocalDate());
            vehicle.setYear(rs.getInt("Year"));
            vehicle.setQuality(rs.getString("Quality"));
            
            listVehicles.add(vehicle);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return listVehicles;
    }
    
    
    public VehicleMgr getVehicleByRegNo(String regno) {
    	VehicleMgr vehicle = new VehicleMgr();
        try {
        	String sql = "SELECT * FROM vehicles WHERE RegNo=?";
            PreparedStatement ps = conn.
                    prepareStatement(sql);
            ps.setString(1, regno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	vehicle.setRegNo(rs.getString("RegNo"));
            	vehicle.setType(rs.getString("Type"));
            	vehicle.setLocation(rs.getString("Location")); 
            	vehicle.setMake(rs.getString("Make"));
            	vehicle.setModelNo(rs.getInt("ModelNo"));
                vehicle.setMileage(rs.getDouble("Mileage"));
                vehicle.setLastServiceDate(rs.getDate("LastServiceDate").toLocalDate());
                vehicle.setYear(rs.getInt("Year"));
                vehicle.setQuality(rs.getString("Quality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
