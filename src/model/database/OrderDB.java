package model.database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import model.Order;

public class OrderDB implements OrderDBIF {

	private Connection connection;
	private PreparedStatement saveOrder;
	private PreparedStatement saveCopyOrder;
	private static final String SAVE_ORDER_Q = "insert into \"Order\" (\"date\", totalPrice, isDelivered, deliveryAdress, customerId, employeeId) values(?, ?, ?, ?, ?, ?)";
	private static final String SAVE_COPY_ORDER_Q = "insert into CopyOrder (copyId, orderId) values (?, ?)";

	public OrderDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveOrder = connection.prepareStatement(SAVE_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
			saveCopyOrder = connection.prepareStatement(SAVE_COPY_ORDER_Q);
		} catch (Exception e) {
			throw new SQLException("Error creating OrderDB", e);
		}
	}

	@Override
	public void saveOrder(Order order) throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		System.out.println(order.getDate() + " " + order.getTotalPrice() + " " + order.isDelivered() + " " + order.getCustomer().getDeliveryAddressId() + " " + order.getCustomer().getId());
		saveOrder.setString(1, order.getDate());
		saveOrder.setDouble(2, order.getTotalPrice());
		saveOrder.setBoolean(3, order.isDelivered());
		saveOrder.setInt(4, order.getCustomer().getDeliveryAddressId());
		saveOrder.setInt(5, order.getCustomer().getId());
		//saveOrder.setInt(6, order.getEmployee().getId());
		saveOrder.setInt(6, 1);
		
		order.setId(con.executeInsertWithIdentity(saveOrder));

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
		saveCopyOrder.setInt(1, copyId);
		saveCopyOrder.setInt(2, orderId);
		saveCopyOrder.executeUpdate();
	}


}
