package model.database;

import model.Seller;
import model.exceptions.DataAccessException;

public interface SellerDBIF {
	public Seller findSellerFromPhone(String phone);

	public void saveSeller(Seller seller) throws DataAccessException;

}
