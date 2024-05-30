package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.exceptions.DataAccessException;

public class EmployeeDB implements EmployeeDBIF {
	private static final String FIND_BY_ID = "select e.id, e.salary, p.fname, p.lname, p.phone, p.email from employee e left join person p on e.personId = p.id where e.id = ?;";
	private PreparedStatement findById;
	
	public EmployeeDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findById = connection.prepareStatement(FIND_BY_ID);
		} 
		catch(SQLException e) {
			System.out.println("Couldn't connect to the database");
		}
	}
	
	public Employee findEmployee(int id) throws DataAccessException {
		Employee employee = null;
		try {
			findById.setInt(1, id);
			ResultSet rs = findById.executeQuery();

			if (rs.next()) {
				employee = buildObject(rs);
			}
		} 
		catch (SQLException e) {
			throw new DataAccessException("Could not read rs", e);
		}
		return employee;
	}
	
	public Employee buildObject(ResultSet rs) throws SQLException {
		Employee employee = new Employee(rs.getString("fname" + " " + rs.getString("lname")),
										rs.getString("phone"),
										rs.getString("email"));
		
		employee.setSalary(rs.getFloat("salary"));
		
		return employee;
	}
}