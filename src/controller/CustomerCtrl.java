package controller;
import model.Customer;
import model.database.CustomerDBIF;
import model.database.DataAccessException;

public class CustomerCtrl {
	private CustomerDBIF customerDB;
	
	public CustomerCtrl(CustomerDBIF customerDB) {
		this.customerDB = customerDB;
	}
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerDB.findCustomer(phoneNo);
	}
	
	public boolean doesCustomerExist(String phoneNo) throws DataAccessException {
		return findCustomer(phoneNo) != null;
	}
}