package controller;

import model.Copy;
import model.database.CarDB;
import model.database.CarDBIF;
import model.database.DataAccessException;

public class CarCtrl {

	private CarDBIF carDB;
	
	public CarCtrl(CarDBIF carDB) {
		this.carDB = carDB;
	
		
	}
	
	public Copy findCopy(String vin) throws DataAccessException{
		return carDB.findCopy(vin);
		
	}
	
}
