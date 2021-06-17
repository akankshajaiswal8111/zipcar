package com.rentaride;
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentLocationDAO {

    private Connection conn;

    public RentLocationDAO() throws ClassNotFoundException, SQLException  {
    	conn = DatabaseManager.initializeDatabase();
    }

    public void addLocation(RentLocation rentLocation) {
        try {
        	String sql = "INSERT INTO rentlocations(name, address, vehicleCapacity, availableCapacity)" +
    		" VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, rentLocation.getName());
            ps.setString(2, rentLocation.getAddress());
            ps.setInt(3, rentLocation.getVehicleCapacity());
            ps.setInt(4, rentLocation.getAvailableCapacity());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeLocation(int id) {
        try {
        	String sql = "DELETE FROM rentlocations WHERE RentLocationId=?";
            PreparedStatement ps = conn
                    .prepareStatement(sql);
            ps.setInt(1, id);
            int numRecords = ps.executeUpdate();
            if(numRecords > 0) return true;

        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
        return false;
      }

    public boolean editLocation(RentLocation rentLocation) throws SQLException{    	
    	System.out.println("In edit location");
    	try {
    		String sql = "UPDATE rentlocations SET name=?, address=?, VehicleCapacity = ? WHERE RentLocationId=?";
    		System.out.println("edit loc...");
    		System.out.println(rentLocation.getName());
    		System.out.println(rentLocation.getAddress());
    		System.out.println(rentLocation.getVehicleCapacity());
    		System.out.println(rentLocation.getId());
            PreparedStatement ps = conn
                    .prepareStatement(sql);
            ps.setString(1, rentLocation.getName());
            ps.setString(2, rentLocation.getAddress()); 
            ps.setInt(3, rentLocation.getVehicleCapacity());
            ps.setInt(4, rentLocation.getId());
            int numRecords = ps.executeUpdate();   
            if(numRecords > 0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    public List<RentLocation> listAllRentLocations() throws SQLException {
        List<RentLocation> listRentLocations = new ArrayList<>();
        try { 
        String sql = "SELECT * FROM rentlocations";
        PreparedStatement ps = conn
                .prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
         
        while (rs.next()) {
        	RentLocation rentLocation = new RentLocation();
        	rentLocation.setId(rs.getInt("RentLocationId"));
        	rentLocation.setName(rs.getString("Name"));
            rentLocation.setAddress(rs.getString("Address")); 
            rentLocation.setVehicleCapacity(rs.getInt("VehicleCapacity"));
            rentLocation.setAvailableCapacity(rs.getInt("AvailableCapacity"));
            listRentLocations.add(rentLocation);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return listRentLocations;
    }
    
    
    public RentLocation getLocationById(int id) {
    	RentLocation rentLocation = new RentLocation();
        try {
        	String sql = "SELECT * FROM rentlocations WHERE RentLocationId=?";
            PreparedStatement ps = conn.
                    prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	rentLocation.setId(rs.getInt("RentLocationId"));
            	rentLocation.setName(rs.getString("Name"));
            	rentLocation.setAddress(rs.getString("Address")); 
            	rentLocation.setVehicleCapacity(rs.getInt("VehicleCapacity"));
            	rentLocation.setAvailableCapacity(rs.getInt("AvailableCapacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentLocation;
    }
    
    public int getCapacityById(int id) {
    	int capacity=0;
    	try {
    		String sql = "Select AvailableCapacity from rentlocations WHERE RentLocationId=?";
    		PreparedStatement ps = conn.
                    prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	capacity = rs.getInt("AvailableCapacity");
            	System.out.println(capacity);
            }
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return capacity ;
    	
    }
    
    public void updateCapacityById(int id, int value) {
    	try {
    		String sql = "Update rentlocations set availablecapacity = ? where RentLocationId= ?";
    		PreparedStatement ps=conn
            .prepareStatement(sql);
    		ps.setInt(1, value);
    		ps.setInt(2, id);
    		ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
