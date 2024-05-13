package model;

public class Customer extends Person{
	public int cvr;
	public int cpr;
	public String address;

	public Customer(String name, String phoneNo, int age, String email,int cvr, int cpr, String adress) {
		super(name, phoneNo, age, email);
		this.cvr = cvr;
		this.cpr = cpr;
		this.address = adress;
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
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}
	

}
