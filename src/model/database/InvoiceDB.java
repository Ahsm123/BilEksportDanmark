package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Invoice;
import model.exceptions.DataAccessException;

public class InvoiceDB implements InvoiceDBIF {
	private Connection connection;
	private PreparedStatement saveInvoice;
	private static final String saveInvoiceQ = 
			"INSERT INTO Invoice (paymentDate, total, orderId) VALUES (?, ? ,?)";


	public InvoiceDB() throws DataAccessException {
		connection = DBConnection.getInstance().getConnection();
		try {
			saveInvoice = connection.prepareStatement(saveInvoiceQ,Statement.RETURN_GENERATED_KEYS);	
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void saveInvoiceInDB(Invoice invoice) throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		
		saveInvoice.setString(1, invoice.getPaymentDate());
		saveInvoice.setDouble(2, invoice.getTotal());
		saveInvoice.setInt(3, invoice.getOrderId());
		
		con.executeInsertWithIdentity(saveInvoice);
		
		con.commitTransaction();
	}
}	