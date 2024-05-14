package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Car;
import model.Copy;



public class CarDB implements CarDBIF {
	
	private static final String FIND_BY_VIN_Q = "SELECT c.*, cp.vin, cp.price, cp.state, cp.modification, cp.kilometer, cp.color, cp.car, cp.taxReturn, cp.isInspected FROM Car c LEFT JOIN Copy cp ON c.id = cp.id where vin = '?'";
	
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
		} catch(SQLException e) {

		}
	}
	
	private Copy buildCopy(ResultSet rs, Car car) throws DataAccessException{
		Copy res = new Copy();
		try {
			res.setVin(rs.getString("vin"));
			res.setPrice(rs.getDouble("price"));
			res.setState(rs.getString("state"));
			res.setModification(rs.getString("modification"));
			res.setKilometer(rs.getInt("kilometer"));
			res.setColor(rs.getString("color"));
			res.setTaxReturn(rs.getBoolean("taxReturn"));
			res.setIsInspected(rs.getBoolean("isInspected"));
		} catch (SQLException e) {
			throw new DataAccessException("Error building common attributes", e);
		} 
		return res;
	}
	
	private Car buildCar(ResultSet rs) throws DataAccessException{
		Car res = new Car();
		try {
			res.setMilage(rs.getInt("milage"));
			res.setManufacturer(rs.getString("manufacturer"));
			res.setModel(rs.getString("model"));
			res.setFuelType(rs.getString("fuelType"));
			res.setCo2Emission(rs.getDouble("co2Emission"));
			res.setHp(rs.getInt("hp"));
			res.setAcceleration(rs.getDouble("acceleration"));
			res.setTopSpeed(rs.getInt("topSpeed"));
			res.setGearType(rs.getString("gearType"));
			res.setNoOfGears(rs.getInt("noOfGears"));
		} catch (SQLException e) {
			throw new DataAccessException("Error building common attributes", e);
		} 
		return res;
	}

	@Override
	public Copy findCopy(String vin) throws DataAccessException {
		Copy res = null;
		try {
			findByVinPs.setString(1, vin);
			ResultSet rs = findByVinPs.executeQuery();

			if (rs.next()) {
				res = buildCopy(rs, buildCar(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}