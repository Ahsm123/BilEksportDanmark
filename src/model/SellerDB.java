package model;

import java.sql.PreparedStatement;

public class SellerDB {
	
	private PreparedStatement saveSellerInfo;
	private static final String SAVE_SELLER_INFO_Q = "insert into Seller (sellerId, sellerAd) values (?, ?)";
	private PreparedStatement saveSellerPerson;
	private static final String SAVE_SELLER_PERSON_Q = "insert into Person (personId, fname, lname, phone, email) values (?, ?, ?, ?, ?)";

}
