package controller;

import java.sql.SQLException;

import model.Invoice;
import model.Order;
import model.database.DataAccessException;
import model.database.InvoiceDB;
import model.database.InvoiceDBIF;

public class InvoiceCtrl {

	private InvoiceDBIF invoiceDB;

	public InvoiceCtrl(InvoiceDBIF invoiceDB)  {
		this.invoiceDB = invoiceDB;

	}

	public void saveInvoiceInDB(Order order) throws SQLException, DataAccessException {
		 invoiceDB.saveInvoiceInDB(order);	

	}
}