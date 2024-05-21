package model;

public class Person {
	public String name;
	public String phoneNo;
	public String email;
	
	public Person (String name, String phoneNo, String email) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}