package controller;

import model.Copy;
import model.database.CarDB;
import model.database.CarDBIF;
import model.database.DataAccessException;

public class CarCtrl {

	private CarDBIF carDB;
	
	public CarCtrl() {
		try { 
			this.carDB = new CarDB();
	}catch (DataAccessException e) {
		
		e.printStackTrace();
	}
	
		
	}
	
	public Copy findCopy(String vin) throws DataAccessException{
		return carDB.findCopy(vin);
		
	}
	
}
