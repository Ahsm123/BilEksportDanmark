package model;

public class Customer extends Person{
	private int id;
	private int cvr;
	private int ssn;
	private String address;

	public Customer(Person person) {
		super(person.getName(), person.getPhoneNo(), person.getEmail());
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}
	
	public int getId() {
		return id;
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
