package model.database;
import model.Customer;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;

public interface CustomerDBIF {
	public Customer findCustomer(String phoneNo) throws DataAccessException, CustomerNotFound;
}