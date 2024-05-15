package controller;

import java.sql.SQLException;

import model.Invoice;
import model.Order;
import model.database.DataAccessException;
import model.database.InvoiceDB;
import model.database.InvoiceDBIF;

public class InvoiceCtrl {

	private InvoiceDBIF invoiceDB;

	public InvoiceCtrl()  {
		try {
			invoiceDB = new InvoiceDB();
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}


	public void saveInvoiceInDB(Order order) throws SQLException, DataAccessException {
		 invoiceDB.saveInvoiceInDB(order);	

	}
}
