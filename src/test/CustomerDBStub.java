package test;

import model.Customer;
import model.Person;
import model.database.CustomerDBIF;

public class CustomerDBStub implements CustomerDBIF {

	@Override
	public Customer findCustomer(String phoneNo) {
		if ("12345678".equals(phoneNo)) {
			Person person = new Person("Thomas Olesen", phoneNo, "ThomasO@ucn.dk");
			Customer customer = new Customer(person);
			customer.setAdress("Gug 99");
			return customer;
		}
		return null; // Customer not found
	}

}