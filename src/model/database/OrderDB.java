package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Copy;
import model.Order;

public class OrderDB implements OrderDBIF {

	private Connection connection;
	private PreparedStatement saveOrder;
	private static final String SAVE_ORDER_Q = "insert into \"Order\" (date, totalPrice, isDelivered, deliveryAddressId, customerId, employeeId)";
	private PreparedStatement saveCopyOrder;
	private static final String SAVE_COPY_ORDER_Q = "insert into CopyOrder (copyId, orderId)";

	public OrderDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveOrder = connection.prepareStatement(SAVE_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
			saveCopyOrder = connection.prepareStatement(SAVE_COPY_ORDER_Q);
		} catch (Exception e) {
			throw new SQLException("Error", e);
		}
	}

	@Override
	public void saveOrder(Order order) throws SQLException {
		confirmOrder(order);
		
		for (Copy copy : order.getCopies()) {
			saveCopyOrder(copy.getId(), order.getId());
		}
	}


	private void saveCopyOrder(int copyId, int orderId) throws SQLException {

		saveCopyOrder.setInt(1, copyId);
		saveCopyOrder.setInt(1, orderId);
	}

	@Override
	public void confirmOrder(Order order) throws SQLException {
		boolean success = false;

		saveOrder.setString(1, order.getDate());
		saveOrder.setDouble(2, order.getTotal());
		saveOrder.setBoolean(3, order.isDelivered());
		saveOrder.setString(4, order.getDeliveryAddress());
		saveOrder.setInt(5, order.getEmployee().getId());
		saveOrder.setInt(6, order.getCustomer().getId());

		ResultSet keys = saveOrder.getGeneratedKeys();
		if (keys.next()) {
			order.setId(keys.getInt(1));
		} else {
			throw new SQLException("Failed so set order id");
		}

	}

}
