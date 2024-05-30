package test;

import model.Invoice;
import model.database.InvoiceDBIF;

public class InvoiceDBStub implements InvoiceDBIF {

	@Override
	public void saveInvoiceInDB(Invoice invoice) {
		System.out.println("Invoice saved for order ID " + invoice.getOrderId());
	}
}