package model;

public class Customer extends Person{
	private int id;
	private int cvr;
	private int ssn;
	private int deliveryAddressId;
	private String address;

	public Customer(String name, String phoneNo, String email) {
		super(name, phoneNo, email);
	}

	public void setDeliveryAddressId(int id) {
		this.deliveryAddressId = id;
	}
	public int getDeliveryAddressId() {
		return deliveryAddressId;
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
	
	public String getCustomerType() {
		String type = "";
		if(ssn == 0) {
			type = "Erhvervskunde";	
		} else {
			type = "Privatkunde";
		}
		return type;
	}
	

}
