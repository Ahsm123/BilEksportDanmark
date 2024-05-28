package test;

import java.sql.SQLException;
import java.util.LinkedList;

import model.BuyInfo;
import model.database.BuyInfoDBIF;
import model.exceptions.DataAccessException;

public class BuyInfoDBStub implements BuyInfoDBIF{
	private LinkedList<BuyInfo> buyOrders;
	
	public BuyInfoDBStub() {
		buyOrders = new LinkedList<>();
	}

	@Override
	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException, DataAccessException {
		buyOrders.add(buyInfo);
		
	}
	
	public LinkedList<BuyInfo> getBuyOrders(){
		return buyOrders;
	}

}
