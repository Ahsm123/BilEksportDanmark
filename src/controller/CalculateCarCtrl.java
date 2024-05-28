package controller;


import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;

import model.BuyInfo;
import model.Copy;
import model.Seller;
import model.api.CarServiceAPI;
import model.database.BuyInfoDB;
import model.database.SellerDB;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.DataAccessException;

public class CalculateCarCtrl {
	private CarServiceAPI carServiceApi;
	private final static double TAX_ROOF = 0.85;
	private final static double AGE_PENALTY = 0.07;
	private final static double KM_PENALTY = 0.01;
	private final static double MIN_PROFIT = 15000;
	private final static double EXPENSES = 5000;

	private BuyInfo latestBuyInfo;

	private BuyInfoCtrl buyInfoCtrl;
	private SellerCtrl sellerCtrl;

	public CalculateCarCtrl() throws SQLException {
		this.buyInfoCtrl = new BuyInfoCtrl(new BuyInfoDB());
		this.carServiceApi = new CarServiceAPI();
		this.sellerCtrl = new SellerCtrl(new SellerDB());
	}

	public Copy importCopy(String vin) throws CarDoesNotMeetRequirementsException {
		Copy copy = carServiceApi.importCopy(vin);
		if(copy.getYear() >= 2004 && copy.getYear() <= 2018 && copy.getKilometer() >= 50000) {
			return copy;
		} 
		else {
			throw new CarDoesNotMeetRequirementsException("Bilen overholder ikke kravene");
		}
	}

	public double calculateOffer(Copy copy, double salesPrice) {
		double taxReturn = calculateTaxReturn(copy);
		double totalIncome = salesPrice + taxReturn;
		double maxOffer = totalIncome - EXPENSES - MIN_PROFIT;

		latestBuyInfo = new BuyInfo(maxOffer, copy);

		return maxOffer;
	}

	private double calculateTaxReturn(Copy copy) {
		double taxReturn = copy.getRegistrationFee() * TAX_ROOF;
		int carAge = Year.now().getValue() - copy.getYear();
		double kmPenalty = (copy.getKilometer()/10000) * KM_PENALTY;

		for(int i = 0; i < carAge; i++){
			taxReturn -= taxReturn * AGE_PENALTY;
		}

		return taxReturn - taxReturn * kmPenalty;
	}

	public void saveBuyInfo(Seller seller) throws SQLException, DataAccessException {
		if(latestBuyInfo == null) {
			throw new NullPointerException();
		}
		latestBuyInfo.setSeller(seller);
		buyInfoCtrl.saveBuyInfo(latestBuyInfo);
	}

	public void setSeller(Seller seller) {
		latestBuyInfo.setSeller(seller);
	}
	
	public Seller findSellerFromPhone(String phone) {
		return sellerCtrl.findSellerFromPhone(phone);
	}
}