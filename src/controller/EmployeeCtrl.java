package controller;

import model.Employee;
import model.database.DataAccessException;
import model.database.EmployeeDB;
import model.database.EmployeeDBIF;

public class EmployeeCtrl {
	private EmployeeDBIF employeeDB;
	
	public EmployeeCtrl() {
		try {
			this.employeeDB = new EmployeeDB();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Employee findEmployee(int id) throws DataAccessException {
		return employeeDB.findEmployee(id);
	}
}