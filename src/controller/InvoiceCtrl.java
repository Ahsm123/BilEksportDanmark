package controller;

import java.sql.SQLException;

import model.Invoice;
import model.Order;
import model.database.InvoiceDB;
import model.database.InvoiceDBIF;
import model.exceptions.DataAccessException;

public class InvoiceCtrl {

	private InvoiceDBIF invoiceDB;

	public InvoiceCtrl(InvoiceDBIF invoiceDB)  {
		this.invoiceDB = invoiceDB;

	}

	public void saveInvoiceInDB(Invoice invoice) throws SQLException, DataAccessException {
		 invoiceDB.saveInvoiceInDB(invoice);	

	}
	
	public Invoice createInvoice(Order order) {
		return new Invoice(order.getDate(), order.getTotalPrice(), order.getId());
	}
}