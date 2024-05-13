package model;

public class Customer extends Person{
	public int cvr;
	public int ssn;
	public String address;

	public Customer(String name, String phoneNo, int age, String email,int cvr, int ssn, String adress) {
		super(name, phoneNo, email);
		this.cvr = cvr;
		this.ssn = ssn;
		this.address = adress;
	}
	
	public Customer(Person person) {
		super(person.getName(), person.getPhoneNo(), person.getEmail());
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}
	

}
