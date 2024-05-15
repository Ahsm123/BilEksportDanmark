package test;

import model.Order;
import model.database.OrderDBIF;

public class OrderDBStub implements OrderDBIF {

	@Override
	public void saveOrder(Order order) {
		System.out.println("Order saved with ID: " + order.getId());

	}
}
