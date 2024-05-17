package model.database;
import java.sql.SQLException;

import model.Copy;
import model.Order;

public interface OrderDBIF {

	public void saveOrder(Order order) throws SQLException, DataAccessException;

	public boolean isCopyInAnOrder(String vin) throws SQLException;

	public void deleteOrder(int orderId) throws SQLException, DataAccessException;
	
	

}
