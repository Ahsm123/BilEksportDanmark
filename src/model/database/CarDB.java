package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Car;
import model.Copy;
import model.exceptions.DataAccessException;



public class CarDB implements CarDBIF {
	
	private static final String FIND_BY_VIN_Q = "SELECT c.*, cp.vin, cp.price, cp.state, cp.modification, cp.kilometer, cp.color, cp.car, cp.taxReturn, cp.isInspected FROM Car c LEFT JOIN Copy cp ON c.id = cp.id where vin = ?";
	
	private PreparedStatement findByVinPs;
	//	private PreparedStatement insertPs;
	
	


	public CarDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findByVinPs = connection.prepareStatement(FIND_BY_VIN_Q );
			//			insertPs = connection.prepareStatement(INSERT_Q);
		} 	
		catch(SQLException e) {
			System.out.println("Couldn't connect to the database");
		}
	}
	
	private Copy buildCopy(ResultSet rs) throws DataAccessException{
		Copy result = null;
		try {
			Copy copy = new Copy(rs.getInt("mileage"),
					rs.getString("manufactorer"),
					rs.getString("model"),
					rs.getString("fuelType"),
					rs.getDouble("co2Emission"),
					rs.getInt("hp"),
					rs.getDouble("acceleration"),
					rs.getInt("topSpeed"),
					rs.getString("gearType"),
					rs.getInt("noOfGears"));
			
			copy.setState(rs.getInt("state"));
			copy.setId(rs.getInt("id"));
			copy.setVin(rs.getString("vin"));
			copy.setPrice(rs.getDouble("price"));
			copy.setModification(rs.getString("modification"));
			copy.setKilometer(rs.getInt("kilometer"));
			copy.setColor(rs.getString("color"));
			copy.setTaxReturn(rs.getBoolean("taxReturn"));
			copy.setIsInspected(rs.getBoolean("isInspected"));
			copy.setRegistrationFee(rs.getDouble("registrationFee"));
			copy.setYear(rs.getInt("year"));
			
			result = copy;
		} 
		catch (SQLException e) {
			throw new DataAccessException("Error building common attributes", e);
		} 
		return result;
	}

	@Override
	public Copy findCopy(String vin) throws DataAccessException {
		Copy res = null;
		try {
			findByVinPs.setString(1, vin);
			ResultSet rs = findByVinPs.executeQuery();

			if (rs.next()) {
				res = buildCopy(rs);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private void setState(Copy copy) {
		
	}

}