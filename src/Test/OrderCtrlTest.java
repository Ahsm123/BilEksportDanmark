package Test;
import controller.*;
import model.database.*;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderCtrlTest {
	
	OrderCtrl orderCtrl;
	private Employee employee;
	private EmployeeProvider employeeProvider;
	
	@BeforeEach
	void setUp() throws DataAccessException, SQLException {
		orderCtrl = new OrderCtrl();
		employeeProvider = new EmployeeProvider();
		employee = employeeProvider.provideEmployee();
	}
	
	@Test
	@DisplayName("Create empty order with Customer and Employee")
	void testCreateOrder() throws NullPointerException, DataAccessException {

		Person person1 = new Person("Thomas", "12345678", "email");
		
		Customer customer = new Customer(person1);
		
		Order currentOrder = orderCtrl.createOrder(customer);
		
		assertNotNull(currentOrder);
		assertEquals(customer, currentOrder.getCustomer());
		assertEquals(employee, currentOrder.getEmployee());
		
	}
	
	
}
;