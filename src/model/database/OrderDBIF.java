package model.database;
import java.sql.SQLException;
import model.Order;

public interface OrderDBIF {
	
	public void saveOrder(Order order) throws SQLException;

}
