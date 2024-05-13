package model.database;

import java.sql.SQLException;

import model.Invoice;
import model.Order;

public interface InvoiceDBIF {
	void saveInvoiceInDB() throws SQLException, DataAccessException;
	
}
