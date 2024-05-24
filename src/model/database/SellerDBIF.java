package model.database;

import java.sql.SQLException;

import model.Seller;
import model.exceptions.DataAccessException;

public interface SellerDBIF {
	void insertSeller(Seller seller) throws SQLException, DataAccessException;

}
