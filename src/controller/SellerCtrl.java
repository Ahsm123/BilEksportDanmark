package controller;

import java.util.HashMap;

import model.Seller;
import model.database.SellerDBIF;

public class SellerCtrl {
	private SellerDBIF sellerDB;
	
	private HashMap<String, Seller> sellers;
	
	public SellerCtrl(SellerDBIF sellerDB) {
		sellers = new HashMap<String, Seller>();
		
		this.sellerDB = sellerDB;
	}
	
	public Seller findSellerFromPhone(String phone) {
		return sellerDB.findSellerFromPhone(phone);
	}
	
	public void addSeller(String phoneNo) {
		if(!sellers.containsKey(phoneNo)) {
        	sellers.put(phoneNo, sellerDB.findSellerFromPhone(phoneNo));
        }
        Seller seller = sellers.get(phoneNo);
	}
}