package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.database.DBConnection;
import model.database.SellerDBIF;
import model.exceptions.DataAccessException;

public class SellerDB implements SellerDBIF{
	private Connection connection;
	private PreparedStatement saveSellerInfo;
	private static final String SAVE_SELLER_INFO_Q = "insert into Seller (sellerId, sellerAd) values (?, ?)";
	private PreparedStatement saveSellerPerson;
	private static final String SAVE_SELLER_PERSON_Q = "insert into Person (personId, fname, lname, phone, email) values (?, ?, ?, ?, ?)";
	
	public SellerDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveSellerInfo = connection.prepareStatement(SAVE_SELLER_INFO_Q);
			saveSellerPerson = connection.prepareStatement(SAVE_SELLER_PERSON_Q);
		} 
		catch (Exception e) {
			throw new SQLException("Error creating OrderDB", e);
		}
	}
	
	@Override
	public void insertSeller(Seller seller) throws SQLException, DataAccessException {
		DBConnection con = DBConnection.getInstance();
		con.startTransaction();
		saveSellerAttributes(seller);
		saveSellerPersonAttributes(seller);
		con.commitTransaction();
	}
	
	private void saveSellerAttributes(Seller seller) throws SQLException, DataAccessException {
		saveSellerInfo.setInt(1, seller.getId());
		saveSellerInfo.setString(2, seller.getCarAd());
	}

	private void saveSellerPersonAttributes(Seller seller) throws SQLException, DataAccessException {
		
		String[] nameParts = seller.getName().split(" ");
		String firstName = nameParts[0];
		String lastName = nameParts.length > 1 ? nameParts[1] : "";
		
		saveSellerPerson.setInt(1, seller.getId());
		saveSellerPerson.setString(2, firstName);
		saveSellerPerson.setString(3, lastName);
		saveSellerPerson.setString(4, seller.getPhoneNo());
		saveSellerPerson.setString(5, seller.getEmail());
	}


}
