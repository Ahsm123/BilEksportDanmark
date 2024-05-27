package controller;

import java.time.Year;

import model.Copy;
import model.api.CarServiceAPI;

public class CalculateCarCtrl {
	private CarServiceAPI carServiceApi;
	private final static double TAX_ROOF = 0.85;
	private final static double AGE_PENALTY = 0.07;
	private final static double KM_PENALTY = 0.01;
	private final static double MIN_PROFIT = 15000;
	private final static double EXPENSES = 5000;

	public CalculateCarCtrl() {
		this.carServiceApi = new CarServiceAPI();
	}

	public Copy importCopy(String vin) {
		return carServiceApi.importCopy(vin);
	}

	public double CalculateOffer(Copy copy, double salesPrice) {
		double taxReturn = calculateTaxReturn(copy);
		double totalIncome = salesPrice + taxReturn;
        double maxOffer = totalIncome - EXPENSES - MIN_PROFIT;
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
}