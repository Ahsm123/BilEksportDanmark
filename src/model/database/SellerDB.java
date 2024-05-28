package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Seller;
import model.exceptions.DataAccessException;

public class SellerDB implements SellerDBIF{
	private Connection connection;
	private PreparedStatement findSellerByCopyId;
	private static final String SAVE_SELLER_INFO_Q = "select s.*, p.fname, p.fname, p.phone, p.email from Seller s left join person p on s.personId = p.id where p.phone = ?";

	public SellerDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			findSellerByCopyId = connection.prepareStatement(SAVE_SELLER_INFO_Q);
		} 
		catch (Exception e) {
			throw new SQLException("Error creating SellerDB", e);
		}
	}

	private Seller buildSeller(ResultSet rs) {
		Seller seller = null;
		try {
			seller = new Seller(rs.getString("fname" + " " + rs.getString("lname")),
					rs.getString("phone"),
					rs.getString("email"));
			
			seller.setCarAd(rs.getString("link"));
			seller.setId(rs.getInt("id"));
		}
		catch (Exception e) {
			
		}
		return seller;
	}

	@Override
	public Seller findSellerFromPhone(String phone) {
		Seller result = null;
		try {
			findSellerByCopyId.setString(1, phone);
			ResultSet rs = findSellerByCopyId.executeQuery();

			if (rs.next()) {
				result = buildSeller(rs);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
}
