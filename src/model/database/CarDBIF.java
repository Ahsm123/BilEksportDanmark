package model.database;

import model.Copy;
import model.exceptions.CopyNotFoundException;
import model.exceptions.DataAccessException;

public interface CarDBIF {
	public Copy findCopy(String vin)throws DataAccessException, CopyNotFoundException;

}