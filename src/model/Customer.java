package model;

public class Customer extends Person{
	int id;
	public int cvr;
	public int ssn;
	public String address;

	public Customer(Person person) {
		super(person.getName(), person.getPhoneNo(), person.getEmail());
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}
	
	public void setId(int id) {
		this.id = id;
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
