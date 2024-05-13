package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.Person;

public interface EmployeeDBIF {
	public Employee findEmployee(int id) throws DataAccessException;
	public Employee buildObject(ResultSet rs, Person person) throws SQLException;
	public Person buildPersonObject(ResultSet rs) throws SQLException;
}