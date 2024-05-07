package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Order;

public class OrderDB implements OrderDBIF {

	private Connection connection;
	private PreparedStatement saveOrder;
	// private static final String SAVE_ORDER_Q =

//	public OrderDB() throws SQLException {
//		try {
//			connection = DBConnection.getInstance().getConnection();
//			saveOrder = connection.prepareStatement(SAVE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
//		}
//		catch(Exception e) {
//			throw new SQLException("Error", e);
//		}
//	}

	@Override
	public void saveOrder(Order order) throws SQLException {

	}

	public boolean confirmOrder(Order order) throws SQLException {

		boolean success = false;

		saveOrder.setInt(1, order.getId());
		saveOrder.setString(2, order.getDate());
//		saveOrder.setFloat(3, order.getTotal());
		saveOrder.setString(4, order.getDeliveryAddress());
		saveOrder.setBoolean(5, order.isDelivered());
//		saveOrder.setInt(6, order.getEmployee().getId());
//		saveOrder.setInt(7, order.getCustomer().getId());
		saveOrder.setString(8, order.getDate());
		
		return success;
	}
}
