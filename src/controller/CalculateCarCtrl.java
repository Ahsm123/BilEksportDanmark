package controller;

import java.time.Year;

import model.Copy;

public class CalculateCarCtrl {
	private Copy copy;
	private final static double TAX_ROOF = 0.85;
	private final static double AGE_PENALTY = 0.07;
	private final static double KM_PENALTY = 0.01;
	private final static double MIN_PROFIT = 5000;
	private final static double EXPENSES = 5000;

	public CalculateCarCtrl() {

	}

	public Copy importCopy(String vin) {
//		CarServiceAPI.fetchCoyp();
		return copy;
	}

	public double CalculateOffer(double taxReturn, double purchasePrice, double salesPrice) {
		double totalIncome = salesPrice + taxReturn;
        double totalExpenses = purchasePrice + EXPENSES;
        double maxOffer = totalIncome - totalExpenses - MIN_PROFIT;
        return maxOffer;
		
	}

	public double calculateTaxReturn(Copy copy) {
		double taxReturn = copy.getRegistrationFee() * TAX_ROOF;
		int carAge = Year.now().getValue() - copy.getYear();
		double kmPenalty = (copy.getKilometer()/10000) * KM_PENALTY;
		
		for(int i = 0; i < carAge; i++){
			taxReturn -= taxReturn * AGE_PENALTY;
		}
		
		return taxReturn - taxReturn * kmPenalty;
	}
}

// tilføjet "year" til copy - husk at ændre i DB + DB klasse + objectBuilder etc. - RegistrationFee skal også tilføjes
// tilføjet BuyInfo, BuyInfoDB, Seller, CalculateCarCtrl