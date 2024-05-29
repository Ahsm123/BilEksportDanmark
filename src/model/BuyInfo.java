package model;

public class BuyInfo {
	private double recommendedPrice;
	private double profit;
	private Seller seller;
	private Copy copy;
	
	public BuyInfo(double recommendedPrice, Copy copy) {
		this.recommendedPrice = recommendedPrice;
		this.copy = copy;
	}
	
	public BuyInfo(double recommendedPrice, Copy copy, Seller seller) {
		this.recommendedPrice = recommendedPrice;
		this.copy = copy;
		this.seller = seller;
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