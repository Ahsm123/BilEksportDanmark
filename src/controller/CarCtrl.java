package controller;

import java.sql.SQLException;

import model.Copy;
import model.database.CarDB;
import model.database.CarDBIF;
import model.exceptions.CopyNotFoundException;
import model.exceptions.DataAccessException;

public class CarCtrl {

	private CarDBIF carDB;
	
	public CarCtrl(CarDBIF carDB) {
		this.carDB = carDB;
	}
	
	public Copy findCopy(String vin) throws DataAccessException, CopyNotFoundException{
		return carDB.findCopy(vin);
	}
	
	public void insertCopy(Copy copy) throws DataAccessException, SQLException {
		carDB.insertCopy(copy);
	}
}