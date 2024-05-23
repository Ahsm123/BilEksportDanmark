package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.BuyInfo;

public class BuyInfoDB implements BuyInfoDBIF {
	private Connection connection;
	
	private PreparedStatement saveBuyInfo;
	private static final String SAVE_BUY_INFO_Q = "";
	
	public BuyInfoDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveBuyInfo = connection.prepareStatement(SAVE_BUY_INFO_Q, Statement.RETURN_GENERATED_KEYS);
			
		}
		catch (Exception e) {
			throw new SQLException("", e);
		}
		
	}
	public void saveBuyInfo(BuyInfo buyInfo) throws SQLException{
		
	}
}
