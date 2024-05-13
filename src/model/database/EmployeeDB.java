package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.Person;

public class EmployeeDB {
	private static final String FIND_BY_ID = "select e.id, e.salary, p.fname, p.lname, p.phone, p.email from employee e left join Person p on e.personId = p.id where e.id = ?;";
	private PreparedStatement findById;
	
	public EmployeeDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findById = connection.prepareStatement(FIND_BY_ID);
		} 
		catch(SQLException e) {
			System.out.println("Couldnt connect to the database");
		}
	}
	
	public Employee findEmployee(int id) throws DataAccessException {
		Employee employee = null;
		try {
			findById.setInt(1, id);
			ResultSet rs = findById.executeQuery();

			if (rs.next()) {
				employee = buildObject(rs, buildPersonObject(rs));
			}
		} 
		catch (SQLException e) {
			throw new DataAccessException("Could not read rs", e);
		}
		return employee;
	}
	
	public Employee buildObject(ResultSet rs, Person person) throws SQLException {
		Employee employee = new Employee(person);
		
		employee.setSalary(rs.getFloat("salary"));
		
		return employee;
	}
	
	public Person buildPersonObject(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String name = rs.getString("fname" + " " + rs.getString("lname"));
		String phone = rs.getString("phone");
		
		return new Person(name, phone, email);
	}
}