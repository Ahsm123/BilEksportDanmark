package model.database;

import model.Seller;

public interface SellerDBIF {
	public Seller findSellerFromPhone(String phone);

}
