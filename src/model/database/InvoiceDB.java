package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import model.Invoice;
import model.Order;
import model.database.*;
import java.sql.*;

public class InvoiceDB implements InvoiceDBIF {

	private InvoiceDBIF invoiceDB;
	private PreparedStatement saveInvoice;
	private static final String saveInvoiceQ = 
			"INSERT INTO Invoice (id,paymentDate, total, orderId) VALUES (?, ?, ? ,?)";


	public InvoiceDB() throws DataAccessException {
		try {
			saveInvoice = DBConnection.getInstance().getConnection().prepareStatement(saveInvoiceQ,Statement.RETURN_GENERATED_KEYS);	
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void saveInvoiceInDB() throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		Invoice invoice = new Invoice();
		saveInvoice.setInt(1, invoice.getId());
		saveInvoice.setString(2, java.sql.Date.valueOf(invoice.getPaymentDate()).toString());
		saveInvoice.setDouble(3, invoice.getTotal());
		saveInvoice.setInt(4,invoice.getOrderId());


		int changedLines = saveInvoice.executeUpdate();
		if(changedLines > 0) {
			ResultSet keys = saveInvoice.getGeneratedKeys();
			if(keys.next()) {
				invoice.setId(keys.getInt(1));
			}
			else {
				throw new SQLException("Failed to set orderid");
			}
			

		}
		con.commitTransaction();

	}
}






	