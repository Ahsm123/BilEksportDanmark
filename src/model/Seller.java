package model;

public class Seller extends Person {
	private String carAd;
	private int id;

	public Seller(String name, String phoneNo, String email) {
		super(name, phoneNo, email);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCarAd() {
		return carAd;
	}

	public void setCarAd(String carAd) {
		this.carAd = carAd;
	}
}
