package controller;

import model.Employee;
import model.database.EmployeeDB;
import model.database.EmployeeDBIF;
import model.exceptions.DataAccessException;

public class EmployeeCtrl {
	private EmployeeDBIF employeeDB;
	
	public EmployeeCtrl(EmployeeDBIF employeeDB) {
		this.employeeDB = employeeDB;
	}
	
	public Employee findEmployee(int id) throws DataAccessException {
		return employeeDB.findEmployee(id);
	}
}