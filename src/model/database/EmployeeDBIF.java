package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.exceptions.DataAccessException;

public interface EmployeeDBIF {
	public Employee findEmployee(int id) throws DataAccessException;
	public Employee buildObject(ResultSet rs) throws SQLException;
}