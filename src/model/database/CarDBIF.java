package model.database;

import java.sql.SQLException;

import model.Copy;
import model.exceptions.CopyNotFoundException;
import model.exceptions.DataAccessException;

public interface CarDBIF {
	public Copy findCopy(String vin)throws DataAccessException, CopyNotFoundException;
	
	public int insertCopy(Copy copy) throws DataAccessException, SQLException;

}