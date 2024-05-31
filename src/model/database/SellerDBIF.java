package model.database;

import java.sql.SQLException;

import model.Seller;
import model.exceptions.DataAccessException;

public interface SellerDBIF {
	public Seller findSellerFromPhone(String phone);

	public void saveSeller(Seller seller) throws DataAccessException, SQLException;

}
