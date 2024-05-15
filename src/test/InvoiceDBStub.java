package test;

import model.Order;
import model.database.InvoiceDBIF;

public class InvoiceDBStub implements InvoiceDBIF {

	@Override
	public void saveInvoiceInDB(Order order) {
		System.out.println("Invoice saved for order ID " + order.getId());

	}

}
