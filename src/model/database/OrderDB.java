package model.database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import model.Copy;
import model.Order;
import model.exceptions.DataAccessException;

public class OrderDB implements OrderDBIF {

	private Connection connection;
	private PreparedStatement saveOrder;
	private PreparedStatement saveCopyOrder;
	private PreparedStatement findAssosiatedOrderCopy;
	private PreparedStatement deleteOrder;
	
	private HashSet<String> alreadyCheckedSoldCopies;
	
	private static final String SAVE_ORDER_Q = "insert into \"Order\" (\"date\", totalPrice, isDelivered, deliveryAdress, customerId, employeeId) values(?, ?, ?, ?, ?, ?)";
	private static final String SAVE_COPY_ORDER_Q = "insert into CopyOrder (copyId, orderId) values (?, ?)";
	private static final String FIND_ORDER_ASSOSIATED_WITH_COPY = "select orderId from CopyOrder where copyId = (select copyId from Copy where vin = ?)";
	private static final String DELETE_ORDER = "delete from \"order\" where id = ?;";

	public OrderDB() throws SQLException {
		try {
			alreadyCheckedSoldCopies = new HashMap<>();
			connection = DBConnection.getInstance().getConnection();
			saveOrder = connection.prepareStatement(SAVE_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
			saveCopyOrder = connection.prepareStatement(SAVE_COPY_ORDER_Q);
			deleteOrder = connection.prepareStatement(DELETE_ORDER);
			findAssosiatedOrderCopy = connection.prepareStatement(FIND_ORDER_ASSOSIATED_WITH_COPY);
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
		saveOrder.setInt(6, order.getEmployeeId());
		
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

	public boolean isCopyInAnOrder(String vin) throws SQLException {
		boolean result = true;
		if(!alreadyCheckedSoldCopies.contains(vin)) {
			findAssosiatedOrderCopy.setString(1, vin);
			result = findAssosiatedOrderCopy.executeQuery().next();
			if(result) {
				alreadyCheckedSoldCopies.add(vin);
			}
		}	
		return result;	
	}
	
	public void deleteOrder(int orderId) throws SQLException {
		deleteOrder.setInt(1, orderId);
		deleteOrder.executeQuery();
	}
}