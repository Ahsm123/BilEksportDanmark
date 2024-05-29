package model.database;

import java.sql.SQLException;

import model.BuyInfo;
import model.exceptions.DataAccessException;

public interface BuyInfoDBIF {
	
	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException, DataAccessException;


}
