package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controller.OrderCtrl;
import model.Customer;
import model.Order;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.CopyAlreadyInOrderException;
import model.exceptions.CopyNotReady;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

public class OrderCtrlUnitTest {

	private OrderCtrl orderCtrl;

	@Before
	public void setup() {
		CustomerDBStub customerDBStub = new CustomerDBStub();
		CarDBStub carDBStub = new CarDBStub();
		InvoiceDBStub invoiceDBStub = new InvoiceDBStub();
		OrderDBStub orderDBStub = new OrderDBStub();

		try {
			orderCtrl = new OrderCtrl(orderDBStub, customerDBStub, carDBStub, invoiceDBStub);
		} catch (DataAccessException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_testCreateOrderWithPhoneNumber() throws DataAccessException, EmptyOrderException, SQLException {
		// Arrange
		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();

		// Act
		orderCtrl.addCopy("1G4HR57Y18U165590");
		orderCtrl.confirmOrder();

		// Assert
		assertEquals("12345678", customer.getPhoneNo());
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 1, order.getCopies().size());
	}

	@Test
	public void TC_02_testCreateOrderWithoutPhoneNumber() throws DataAccessException {
		
		assertThrows(CustomerNotFound.class, () -> orderCtrl.createOrder(" ", 1));
	}

	@Test
	public void TC_03_testAddCarThatIsNotInspected()
			throws DataAccessException, EmptyOrderException, SQLException {

		assertThrows(CopyNotReady.class, () -> orderCtrl.addCopy("1HGCT1B73EA082703"));


	}

	@Test
	public void TC_04_testCreateOrderWithPhoneNumberAndSoldCar()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange
		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();
		// Act
		assertThrows(CarAlreadySoldException.class, ()->orderCtrl.addCopy("5NPEB4AC7CH325431"));

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 0, order.getCopies().size());
	}

	@Test
	public void TC_05_testCreateOrderWithPhoneNumberAndSoldCarPlusOne()
			throws DataAccessException, EmptyOrderException, SQLException {

		Order order1 = orderCtrl.createOrder("12345678", 1);
		Customer customer = order1.getCustomer();

		orderCtrl.addCopy("1G4HR57Y18U165590");
		assertThrows(CarAlreadySoldException.class, ()-> orderCtrl.addCopy("5NPEB4AC7CH325431"));
		
		orderCtrl.confirmOrder();

		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order1);
		assertEquals("Order should have one car", 1, order1.getCopies().size());
	}

}