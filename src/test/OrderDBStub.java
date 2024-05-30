package test;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Copy;
import model.Order;
import model.database.OrderDBIF;
import model.exceptions.DataAccessException;

public class OrderDBStub implements OrderDBIF {

	private LinkedList<Copy> copies;
	
	public OrderDBStub() {
		copies = new LinkedList<>();
	}
	
	@Override
	public void saveOrder(Order order) {
		System.out.println("Order saved with ID: " + order.getId());

	}

	@Override
	public boolean isCopyInAnOrder(String vin) throws SQLException {
		if(vin.equals("5NPEB4AC7CH325431")) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteOrder(int orderId) throws SQLException, DataAccessException {
		//new OrderCtrl().deleteOrder(orderId);
		
	}
}
