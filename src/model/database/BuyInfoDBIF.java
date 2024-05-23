package model.database;

import java.sql.SQLException;

import model.BuyInfo;

public interface BuyInfoDBIF {
	
	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException;

}
