package controller;

import model.Employee;

public class EmployeeProvider {
	private Employee employee;

	public EmployeeProvider() {
		employee.setId(1);
		employee.setSalary(20000);
		employee.setName("Thomas");
		employee.setAge(20);
		employee.setEmail("Thomas@bileksport.dk");
		employee.setPhoneNo("12345678");

	}

	public Employee provideEmployee() {
		return employee;
	}

}
