package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import model.Person;

public class CustomerDB implements CustomerDBIF {
				
 private static final String FIND_CUSTOMER_BY_PHONE = "select c.id, c.street, c.streetno, c.cvr, c.ssn,"
			+ " p.fname, p.lname, p.phone, p.email, p.type, "
			+ "da.street, da.streetno, pc.city, co.country"
			+ "from customer c join person p on c.personid = p.id left join "
			+ "deliveryaddress da on c.deliveryadress = da.id left join postalcode pc on da.postalcode = pc.postalcode"
			+ "left join country co on pc.city = co.city where p.phone = ?";
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
		private Customer createCustomerObject(ResultSet rs) throws DataAccessException {
			Customer currCustomer = new Customer();
			try {
				Person person = new Person();
				
				
				currCustomer.setCvr(rs.getInt("cvr"));
			}
				
			catch (SQLException e) {
				e.printStackTrace();
			
			}
			return currCustomer;
		}
		

		@Override
		public Customer findCustomer(String phoneNo) throws DataAccessException {
			Customer res =  null;
			
			try {
				findCustomer.setString(1,phoneNo);
				ResultSet rs = findCustomer.executeQuery();
				if (rs.next()) {
					res = createCustomerObject(rs);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return res;
		}
}
