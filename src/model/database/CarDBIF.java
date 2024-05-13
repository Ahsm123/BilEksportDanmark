package model.database;


import java.sql.SQLException;

import model.Copy;

public interface CarDBIF {
	public Copy findCopy(String vin)throws SQLException;

}
