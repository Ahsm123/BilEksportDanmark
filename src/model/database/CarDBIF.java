package model.database;


import model.Copy;

public interface CarDBIF {
	public Copy findCopy(String vin)throws DataAccessException;

}
