package model.database;

import java.sql.SQLException;

import model.Invoice;
import model.Order;
import model.exceptions.DataAccessException;

public interface InvoiceDBIF {
	void saveInvoiceInDB(Invoice invoice) throws SQLException, DataAccessException;
}