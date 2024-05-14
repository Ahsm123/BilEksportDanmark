package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import model.Person;

public class CustomerDB implements CustomerDBIF {

	private static final String FIND_CUSTOMER_BY_PHONE = "select c.id, c.cvr, c.ssn,"
			+ " p.fname, p.lname, p.phone, p.email, p.type, da.street, da.streetno, pc.city, co.country from customer c "
			+ " join person p on c.personid = p.id left join DeliveryAdress da on c.deliveryAdress = da.id"
			+ " left join postalcode pc on da.postalcode = pc.postalcode "
			+ " left join country co on pc.city = co.city where p.phone = ?";
	private PreparedStatement findCustomer;




	public CustomerDB() throws DataAccessException {
		init();
	}


	private void init() throws DataAccessException{
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findCustomer = con.prepareStatement(FIND_CUSTOMER_BY_PHONE);
			// Statement.RETURN_GENERATED_KEYS as a second argument
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	//Combine name til en
	//Combine Address til en
	private Customer createCustomerObject(ResultSet rs, Person person) throws DataAccessException, SQLException {
		Customer customer = new Customer(person);
		int cvr = rs.getInt("cvr");
		if (cvr == 0) {
			customer.setSsn(rs.getInt("ssn"));
		} 
		else {
			customer.setCvr(cvr); 
		}

		customer.setAdress(rs.getString("street") + " "
				+ rs.getInt("streetNo") + " "
				+ rs.getInt("postalCode") + " "
				+ rs.getString("city") + " "
				+ rs.getString("country"));

		return customer;
	}

	public Person buildPersonObject(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String name = rs.getString("fname" + " " + rs.getString("lname"));
		String phone = rs.getString("phone");

		return new Person(name, phone, email);
	}

	@Override
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		Customer res =  null;

		try {
			findCustomer.setString(1,phoneNo);
			ResultSet rs = findCustomer.executeQuery();
			if (rs.next()) {
				res = createCustomerObject(rs, buildPersonObject(rs));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
