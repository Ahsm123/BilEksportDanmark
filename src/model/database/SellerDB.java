package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Seller;
import model.exceptions.DataAccessException;

public class SellerDB implements SellerDBIF{
	private Connection connection;
	private PreparedStatement findSellerByPhone;
	private PreparedStatement savePerson;
	private PreparedStatement saveSeller;
	private static final String FIND_SELLER_FROM_PHONE_Q = "select s.*, p.fname, p.lname, p.phone, p.email from Seller s left join person p on s.personId = p.id where p.phone = ?";
	private static final String SAVE_PERSON_Q = "insert into person(fName, lName, phone, email, type) values(?, ?, ?, ?, ?)";
	private static final String SAVE_SELLER_Q = "insert into seller(sellerAd, personId) values(?, ?)";


	public SellerDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			findSellerByPhone = connection.prepareStatement(FIND_SELLER_FROM_PHONE_Q);
			savePerson = connection.prepareStatement(SAVE_PERSON_Q, Statement.RETURN_GENERATED_KEYS);
			saveSeller = connection.prepareStatement(SAVE_SELLER_Q);

		} 
		catch (Exception e) {
			throw new SQLException("Error creating SellerDB", e);
		}
	}

	private Seller buildSeller(ResultSet rs) throws SQLException {
		Seller seller = null;

		seller = new Seller(rs.getString("fname") + " " + rs.getString("lname"),
				rs.getString("phone"),
				rs.getString("email"));

		seller.setCarAd(rs.getString("sellerAd"));
		seller.setId(rs.getInt("personId"));

		return seller;
	}

	public void saveSeller(Seller seller) throws DataAccessException, SQLException {
		DBConnection dbConnection = DBConnection.getInstance();

		try {
			dbConnection.startTransaction();

			String[] name = seller.getName().split(" ");

			savePerson.setString(1, name[0]);
			savePerson.setString(2, name[1]);
			savePerson.setString(3, seller.getPhoneNo());
			savePerson.setString(4, seller.getEmail());
			savePerson.setString(5, "Seller");

			int personId = dbConnection.executeInsertWithIdentity(savePerson);

			saveSeller.setString(1, seller.getCarAd());
			saveSeller.setInt(2, personId);

			saveSeller.execute();

			dbConnection.commitTransaction();
		}
		catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public Seller findSellerFromPhone(String phone) {
		Seller result = null;
		try {
			findSellerByPhone.setString(1, phone);
			ResultSet rs = findSellerByPhone.executeQuery();

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