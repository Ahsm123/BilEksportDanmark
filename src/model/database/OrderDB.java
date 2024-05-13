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
	public void saveOrder(Order order) throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		
		confirmOrder(order);
		
		order.getCopies().forEach(copy -> {
			try {
				saveCopyOrder(copy.getId(), order.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		con.commitTransaction();
	}



	private void saveCopyOrder(int copyId, int orderId) throws SQLException {
        
		saveCopyOrder = connection.prepareStatement(SAVE_COPY_ORDER_Q);
        saveCopyOrder.setInt(1, copyId);
        saveCopyOrder.setInt(2, orderId);
        saveCopyOrder.executeUpdate();
	}

	@Override
	public void confirmOrder(Order order) throws SQLException, DataAccessException {

		DBConnection con = DBConnection.getInstance();
		con.startTransaction();

		saveOrder.setString(1, order.getDate());
		saveOrder.setDouble(2, order.getTotalPrice());
		saveOrder.setBoolean(3, order.isDelivered());
		saveOrder.setString(4, order.getDeliveryAddress());
		saveOrder.setInt(5, order.getEmployee().getId());
//		saveOrder.setInt(6, order.getCustomer().getId());

		int changedLines = saveOrder.executeUpdate();
		if (changedLines > 0) {
			ResultSet keys = saveOrder.getGeneratedKeys();
			if (keys.next()) {
				order.setId(keys.getInt(1));
			} else {
				throw new SQLException("Failed so set order id");
			}

		}
		con.commitTransaction();

	}
}
