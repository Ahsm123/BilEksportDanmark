package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import controller.OrderCtrl;
import model.Copy;
import model.Customer;
import model.Order;
import model.database.CarDB;
import model.database.CustomerDB;
import model.database.DBConnection;
import model.database.InvoiceDB;
import model.database.OrderDB;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CopyNotReady;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

public class OrderCtrlIntegrationTest {

	private OrderCtrl orderCtrl;
	private Connection connection;

	@Before
	public void setup() throws DataAccessException, SQLException {

		DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();

		CustomerDB customerDB = new CustomerDB();
		CarDB carDB = new CarDB();
		InvoiceDB invoiceDB = new InvoiceDB();
		OrderDB orderDB = new OrderDB();

		try {
			orderCtrl = new OrderCtrl(orderDB, customerDB, carDB, invoiceDB);
		} catch (DataAccessException | SQLException e) {
			e.printStackTrace();
		}

		setUpDatabase();
	}

	private void setUpDatabase() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("DELETE FROM \"Order\"");

		}
	}

	@After
	public void teardown() throws SQLException {
		tearDownDatabase();
	}

	private void tearDownDatabase() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("DELETE FROM \"Order\"");

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
		assertThrows(CarAlreadySoldException.class, () -> orderCtrl.addCopy("5NPEB4AC7CH325431"));

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 0, order.getCopies().size());
	}

	@Test
	public void TC_05_testCreateOrderWithPhoneNumberAndSoldCarPlusOne()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange
		orderCtrl.createOrder("12345678", 1);
		orderCtrl.addCopy("5NPEB4AC7CH325431");
		orderCtrl.confirmOrder();
		
		
		Order order1 = orderCtrl.createOrder("12345678", 1);
		Customer customer = order1.getCustomer();
		// Act
		orderCtrl.addCopy("1G4HR57Y18U165590");
		assertThrows(CarAlreadySoldException.class, () -> orderCtrl.addCopy("5NPEB4AC7CH325431"));

		orderCtrl.confirmOrder();

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order1);
		assertEquals("Order should have one car", 1, order1.getCopies().size());
	}

}