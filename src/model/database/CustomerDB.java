package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;

public class CustomerDB implements CustomerDBIF {
	private Connection connection;
	private PreparedStatement findCustomer;
	private static final String FIND_CUSTOMER_BY_PHONE = "select c.id, c.cvr, c.ssn,"
			+ " p.fname, p.lname, p.phone, p.email, p.type, da.street, da.streetno, pc.city, pc.postalCode, da.id as deliveryAddressId, co.country from customer c "
			+ " join person p on c.personid = p.id left join DeliveryAdress da on c.deliveryAdress = da.id"
			+ " left join postalcode pc on da.postalcode = pc.postalcode "
			+ " left join country co on pc.city = co.city where p.phone = ?";
	
	public CustomerDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException{
		connection = DBConnection.getInstance().getConnection();
		try {
			findCustomer = connection.prepareStatement(FIND_CUSTOMER_BY_PHONE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Customer createCustomerObject(ResultSet rs) throws DataAccessException, SQLException {
		Customer customer = new Customer(rs.getString("fname") + " " + rs.getString("lname"),  
										rs.getString("phone"), 
										rs.getString("email"));
		
		customer.setAdress(rs.getString("street") + " "
				+ rs.getInt("streetNo") + " "
				+ rs.getString("postalCode") + " "
				+ rs.getString("city") + " "
				+ rs.getString("country"));
		customer.setSsn(rs.getInt("ssn"));
		customer.setCvr(rs.getInt("cvr")); 
		customer.setId(rs.getInt("id"));
		customer.setDeliveryAddressId(rs.getInt("deliveryAddressId"));
		
		return customer;
	}

	@Override
	public Customer findCustomer(String phoneNo) throws DataAccessException, CustomerNotFound {
		Customer result =  null;

		try {
			findCustomer.setString(1,phoneNo);
			ResultSet rs = findCustomer.executeQuery();
			if (rs.next()) {
				result = createCustomerObject(rs);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == null) {
			throw new CustomerNotFound("Kunde ikke fundet");
		}
		return result;
	}
}