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
	
	private PreparedStatement saveInvoice;
	private static final String saveInvoiceQ = 
			"INSERT INTO Invoice (paymentDate, total, orderId) VALUES (?, ? ,?)";


	public InvoiceDB() throws DataAccessException {
		try {
			saveInvoice = DBConnection.getInstance().getConnection().prepareStatement(saveInvoiceQ,Statement.RETURN_GENERATED_KEYS);	
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void saveInvoiceInDB(Order order) throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		
		saveInvoice.setString(1, order.getDate());
		saveInvoice.setDouble(2, order.getTotalPrice());
		saveInvoice.setInt(3, order.getId());
		
		con.executeInsertWithIdentity(saveInvoice);
		
		con.commitTransaction();
	}
}	