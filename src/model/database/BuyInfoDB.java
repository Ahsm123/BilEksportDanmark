package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.BuyInfo;
import model.Copy;
import model.Seller;
import model.exceptions.DataAccessException;

public class BuyInfoDB implements BuyInfoDBIF {
	private Connection connection;

	private PreparedStatement saveBuyInfo;
	private static final String SAVE_BUY_INFO_Q = "insert into BuyInfo (copyId, sellerId, offer) values (?, ?, ?)";
	
	public BuyInfoDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveBuyInfo = connection.prepareStatement(SAVE_BUY_INFO_Q);
			
		} catch (Exception e) {
			throw new SQLException("", e);
		}

	}

	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException, DataAccessException{
			DBConnection con = DBConnection.getInstance();
			con.startTransaction();
			saveBuyInfo.setInt(1, buyInfo.getCopy().getId());
			saveBuyInfo.setInt(2, buyInfo.getSeller().getId());
			saveBuyInfo.setDouble(3, buyInfo.getRecommendedPrice());
			saveBuyInfo.execute();
			con.commitTransaction();
	}
	

}

