package model.database;
import java.sql.SQLException;
import model.Order;

public interface OrderDBIF {

	public void saveOrder(Order order) throws SQLException;
	public void confirmOrder(Order order) throws SQLException;
	
	

}
