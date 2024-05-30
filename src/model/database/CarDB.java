package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Copy;
import model.exceptions.CopyNotFoundException;
import model.exceptions.DataAccessException;



public class CarDB implements CarDBIF {
	private Connection connection;
	private static final String FIND_BY_VIN_Q = " SELECT c.mileage, c.manufactorer, c.model, c.fuelType, c.hp, c.co2Emission, c.acceleration, c.topSpeed, c.gearType, c.noOfGears, cp.* FROM \"Copy\" cp LEFT JOIN Car c ON c.id = cp.car where cp.vin = ?";
	private static final String INSERT_INTO_CAR = "insert into car(mileage, manufactorer, model, fuelType, hp, co2Emission, acceleration, topSpeed, gearType, noOfGears) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_INTO_COPY = "insert into \"copy\"(vin, \"state\", modification, kilometer, color, car, taxReturn, isInspected, year, registrationFee, purchasePrice, salesPrice) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private PreparedStatement findByVinPs;
	private PreparedStatement insertIntoCar;
	private PreparedStatement insertIntoCopy;
	//	private PreparedStatement insertPs;
	
	


	public CarDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		connection = DBConnection.getInstance().getConnection();
		try {
			findByVinPs = connection.prepareStatement(FIND_BY_VIN_Q);
			insertIntoCar = connection.prepareStatement(INSERT_INTO_CAR, Statement.RETURN_GENERATED_KEYS);
			insertIntoCopy = connection.prepareStatement(INSERT_INTO_COPY, Statement.RETURN_GENERATED_KEYS);
			
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
			copy.setSalesPrice(rs.getDouble("salesPrice"));
			copy.setPurchasePrice(rs.getDouble("purchasePrice"));
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
	public Copy findCopy(String vin) throws DataAccessException, CopyNotFoundException {
		Copy result = null;
		try {
			findByVinPs.setString(1, vin);
			ResultSet rs = findByVinPs.executeQuery();

			if (rs.next()) {
				result = buildCopy(rs);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == null) {
			throw new CopyNotFoundException("Copy ikke fundet");
		}
		return result;
	}
	
	public int insertCopy(Copy copy) throws DataAccessException, SQLException {
		DBConnection dbConnection = DBConnection.getInstance();
		dbConnection.startTransaction();
		
		insertIntoCar.setInt(1, copy.getMilage());
		insertIntoCar.setString(2, copy.getManufacturer());
		insertIntoCar.setString(3, copy.getModel());
		insertIntoCar.setString(4, copy.getFuelType());
		insertIntoCar.setInt(5, copy.getHp());
		insertIntoCar.setDouble(6, copy.getCo2Emission());
		insertIntoCar.setDouble(7, copy.getAcceleration());
		insertIntoCar.setInt(8, copy.getTopSpeed());
		insertIntoCar.setString(9, copy.getGearType());
		insertIntoCar.setInt(10, copy.getNoOfGears());
		
		int id = dbConnection.executeInsertWithIdentity(insertIntoCar);
		
		insertIntoCopy.setString(1, copy.getVin());
		insertIntoCopy.setInt(2, copy.getState().ordinal()+1);
		insertIntoCopy.setString(3, copy.getModification());
		insertIntoCopy.setInt(4, copy.getKilometer());
		insertIntoCopy.setString(5, copy.getColor());
		insertIntoCopy.setInt(6, id);
		insertIntoCopy.setBoolean(7, copy.isTaxReturn());
		insertIntoCopy.setBoolean(8, copy.isInspected());
		insertIntoCopy.setInt(9, copy.getYear());
		insertIntoCopy.setDouble(10, copy.getRegistrationFee());
		insertIntoCopy.setDouble(11, copy.getPurchasePrice());
		insertIntoCopy.setDouble(12, copy.getSalesPrice());
		
		id = dbConnection.executeInsertWithIdentity(insertIntoCopy);
		
		dbConnection.commitTransaction();
		
		return id;
	}
}