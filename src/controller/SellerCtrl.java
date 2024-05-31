package controller;

import java.sql.SQLException;

import model.Seller;
import model.database.SellerDBIF;
import model.exceptions.DataAccessException;

public class SellerCtrl {
	private SellerDBIF sellerDB;
	
	public SellerCtrl(SellerDBIF sellerDB) {
		this.sellerDB = sellerDB;
	}
	
	public Seller findSellerFromPhone(String phone) {
		return sellerDB.findSellerFromPhone(phone);
	}
	
	public void saveSeller(String name, String phone, String email, String link) throws DataAccessException, SQLException {
		Seller seller = new Seller(name, phone, email);
		seller.setCarAd(link);
		sellerDB.saveSeller(seller);
	}
}