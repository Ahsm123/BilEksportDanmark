package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Car;
import model.Copy;



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
		} catch(SQLException e) {

		}
	}
	
	private Copy buildCopy(ResultSet rs) throws DataAccessException{
		Copy result = null;
		try {
			Copy res = new Copy(rs.getInt("mileage"),
					rs.getString("manufactorer"),
					rs.getString("model"),
					rs.getString("fuelType"),
					rs.getDouble("co2Emission"),
					rs.getInt("hp"),
					rs.getDouble("acceleration"),
					rs.getInt("topSpeed"),
					rs.getString("gearType"),
					rs.getInt("noOfGears"));
			result = res;
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}