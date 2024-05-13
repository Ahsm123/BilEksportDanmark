package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Car;
import model.Copy;


public class CarDB implements CarDBIF {
	
	private Copy buildCopy(Copy copy, ResultSet rs) throws DataAccessException{
		Copy res = copy;
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
		return copy;
	}
	
	private Car buildCar(Car car, ResultSet rs) throws DataAccessException{
		Car res = car;
		try {
			res.setMilage(rs.getInt("milage"));
			res.setManufacturer(rs.getString("manufacturer"));
			res.setModel(rs.getString("model"));
			res.setFuelType(rs.getString("fuelType"));
			res.setCo2Emission(rs.getDouble("co2Emission"));
			res.setPower(rs.getInt("power"));
			res.setAcceleration(rs.getDouble("acceleration"));
			res.setTopSpeed(rs.getInt("topSpeed"));
			res.setGearType(rs.getString("gearType"));
			res.setNoOfGears(rs.getInt("noOfGears"));
		} catch (SQLException e) {
			throw new DataAccessException("Error building common attributes", e);
		} 
		return car;
	}

	@Override
	public Copy findCopy(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

}