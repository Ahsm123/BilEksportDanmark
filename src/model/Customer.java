package model;

public class Customer extends Person{
	public int cvr;
	public int cpr;
	public String adress;

	public Customer(String Name, String phoneNo, int age, String email,int cvr, int cpr, String adress) {
		super(Name, phoneNo, age, email);
		this.cvr = cvr;
		this.cpr = cpr;
		this.adress = adress;
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}

	public int getCpr() {
		return cpr;
	}

	public void setCpr(int cpr) {
		this.cpr = cpr;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	

}
