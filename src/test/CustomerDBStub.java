package test;

import model.Customer;
import model.Person;
import model.database.CustomerDBIF;

public class CustomerDBStub implements CustomerDBIF {

	@Override
	public Customer findCustomer(String phoneNo) {
		if ("12345678".equals(phoneNo)) {
			Customer customer = new Customer("Thomas Olesen", phoneNo, "ThomasO@ucn.dk");
			customer.setAdress("Gug 99");
			return customer;
		}
		return null; // Customer not found
	}

}