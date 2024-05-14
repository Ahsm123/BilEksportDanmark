package controller;

import model.Employee;
import model.Person;

public class EmployeeProvider {
	private Employee employee;

	public EmployeeProvider() {
		Person person = new Person("Thomas", "@", "12345678");
	
		Employee employee = new Employee(person);
		
		employee.setId(1);
		employee.setSalary(20000);
		employee.setAge(20);


	}

	public Employee provideEmployee() {
		return employee;
	}

}
