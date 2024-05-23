package model;

public class BuyInfo {
	private double recommendedPrice;
	private double profit;
	private Seller seller;
	private Copy copy;
	
	public BuyInfo(double recommendedPrice, double profit, Seller seller, Copy copy) {
		this.recommendedPrice = recommendedPrice;
		this.profit = profit;
		this.seller = seller;
		this.copy = copy;
		
	}
	
	

	public double getRecommendedPrice() {
		return recommendedPrice;
	}

	public void setRecommendedPrice(double recommendedPrice) {
		this.recommendedPrice = recommendedPrice;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}
	
	

}
