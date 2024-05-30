package controller;

import java.sql.SQLException;

import model.BuyInfo;
import model.Copy;
import model.database.BuyInfoDBIF;
import model.exceptions.DataAccessException;

public class BuyInfoCtrl {
	private BuyInfoDBIF buyInfoDB;
	
	public BuyInfoCtrl(BuyInfoDBIF buyInfoDB) {
		this.buyInfoDB = buyInfoDB;
	}
	
	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException, DataAccessException {
		buyInfoDB.saveBuyInfo(buyInfo);
	}
	
	public BuyInfo createBuyInfo(double maxOffer, Copy copy) {
		return new BuyInfo(maxOffer, copy);
	}
}