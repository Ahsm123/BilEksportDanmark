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
	private CarDB carDB;
	private SellerDB sellerDB;

	private PreparedStatement saveBuyInfo;
	private static final String SAVE_BUY_INFO_Q = "insert into BuyInfo (copyId, sellerId, offer) values (?, ?, ?)";
	
	private PreparedStatement findBuyInfoByCopyId;
	private static final String FIND_BUY_INFO_BY_COPY_ID_Q = 
		        "SELECT bi.copyId, bi.sellerId, bi.offer, p.phone AS seller_phone, cp.vin AS car_vin, " +
		        "s.sellerAd, p.fname, p.lname, p.email, " +
		        "c.mileage, c.manufactorer, c.model, c.fuelType, c.hp, c.co2Emission, c.acceleration, " +
		        "c.topSpeed, c.gearType, c.noOfGears, cp.* " +
		        "FROM BuyInfo bi " +
		        "JOIN copy cp ON bi.copyId = cp.id " +
		        "JOIN car c ON c.id = cp.car " +
		        "JOIN seller s ON bi.sellerId = s.personId " +
		        "JOIN person p ON s.personId = p.id " +
		        "WHERE bi.copyId = ?";

	public BuyInfoDB() throws SQLException {
		try {
			connection = DBConnection.getInstance().getConnection();
			saveBuyInfo = connection.prepareStatement(SAVE_BUY_INFO_Q);
			findBuyInfoByCopyId = connection.prepareStatement(FIND_BUY_INFO_BY_COPY_ID_Q);
			
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
	
    public BuyInfo getBuyInfoByCopyId(int copyId) throws SQLException, DataAccessException {
        BuyInfo buyInfo = null;
        findBuyInfoByCopyId.setInt(1, copyId);
        ResultSet rs = findBuyInfoByCopyId.executeQuery();
        if (rs.next()) {
            String vin = rs.getString("car_vin");
            String phone = rs.getString("seller_phone");

            Copy copy = carDB.findCopy(vin);
            Seller seller = sellerDB.findSellerFromPhone(phone);

            buyInfo = new BuyInfo(rs.getDouble("offer"), copy, seller);
        }
        return buyInfo;
    }
}

