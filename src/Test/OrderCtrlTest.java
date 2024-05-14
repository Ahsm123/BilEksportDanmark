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
	
	@BeforeEach
	void setUp() throws DataAccessException, SQLException {
		orderCtrl = new OrderCtrl();
	}
	
	@Test
	@DisplayName("Create empty order with Customer and Employee")
	void testCreateOrder() throws NullPointerException, DataAccessException {
		
		Person person1 = new Person("Thomas", "12345678", "email");
		Person person2 = new Person("Anders", "87654321", "email");
		
		Customer customer = new Customer(person1);
		Employee employee = new Employee(person2);
		
		Order order = orderCtrl.createOrder(customer);
		
		assertNotNull(order);
		assertEquals(customer, order.getCustomer());
		assertEquals(employee, order.getEmployee());
		
	}
	
	
}
;