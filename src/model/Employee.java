package model;

public class Employee extends Person {
	public int id;
	public double salary;

	public Employee(String name, String phoneNo, String email) {
		super(name, phoneNo, email);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	

}
