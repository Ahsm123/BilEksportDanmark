package test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.CalculateCarCtrl;

import model.BuyInfo;
import model.Copy;
import model.Seller;
import model.database.BuyInfoDB;
import model.database.DBConnection;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.DataAccessException;

public class CalculateCarIntegrationsTest {
	private CalculateCarCtrl calculateCarCtrl;
	private BuyInfoDB buyInfoDB;
	private Connection connection;

	@Before
	public void setUp() throws SQLException, DataAccessException {

		DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();

		calculateCarCtrl = new CalculateCarCtrl();
		buyInfoDB = new BuyInfoDB();

		setUpDatabase();

	}

	private void setUpDatabase() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("DELETE FROM \"BuyInfo\"");

		}
	}
	
	@After
	public void teardown() throws SQLException {
		setUpDatabase();
	}

	@Test
	public void TC_01_calculateOfferTest()
			throws CarDoesNotMeetRequirementsException, SQLException, DataAccessException {
	
		Copy copy = calculateCarCtrl.importCopy("5NPEB4AC5CH354720");
		calculateCarCtrl.calculateOffer(copy, 125000);
		
		Seller seller = new Seller("DummySeller", "99999999", "Dummy@Seller.dk");
		seller.setCarAd("Bilbasen.dk");
		seller.setId(99);
		
		BuyInfo latestBuyInfo = calculateCarCtrl.getLatestBuyInfo();
		latestBuyInfo.setSeller(seller);
		
		buyInfoDB.saveBuyInfo(latestBuyInfo);
		
		BuyInfo retrievedBuyInfo = buyInfoDB.getBuyInfoByCopyId(copy.getId());
		
		assertNotNull("BuyInfo should not be null", retrievedBuyInfo);
		assertEquals(copy.getId(), retrievedBuyInfo.getCopy().getId());
		assertEquals(125000, retrievedBuyInfo.getRecommendedPrice(), 0.01);
		assertEquals(copy.getVin(), retrievedBuyInfo.getCopy().getVin());
		assertNotNull("Seller should not be null", retrievedBuyInfo.getSeller());

		


	}

}
