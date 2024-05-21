package gui;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.database.DBConnection;
import model.exceptions.DataAccessException;

public class MainPage extends GUIPanel {
	private static final long serialVersionUID = 1L;

	public MainPage() {
		super(450, 300);
		init();
		createButtonsPanel();
		createHeaderPanel();
	}

	private void init() {
		try {
			DBConnection.getInstance();
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void createButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, BorderLayout.CENTER);
		buttonsPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel orderMenuPanel = new JPanel();
		buttonsPanel.add(orderMenuPanel);
		orderMenuPanel.setLayout(new CardLayout(0, 0));
		
		JButton btnOrderMenu = new JButton("Order menu");
		btnOrderMenu.addActionListener(e -> goOrderMenu());

		orderMenuPanel.add(btnOrderMenu, "name_8201971681900");
		
		JButton btnCustomerMenu = new JButton("Kunde menu");
		buttonsPanel.add(btnCustomerMenu);
		
		JPanel carMenuPanel = new JPanel();
		buttonsPanel.add(carMenuPanel);
		carMenuPanel.setLayout(new CardLayout(0, 0));
		
		JButton btnProductMenu = new JButton("Bil menu");
		carMenuPanel.add(btnProductMenu, "name_8207516186800");
		
		JButton btnSalesAssistant = new JButton("Medarbejder menu");
		buttonsPanel.add(btnSalesAssistant);
	}
	
	private void createHeaderPanel() {
		JPanel titleHeaderPanel = new JPanel();
		contentPane.add(titleHeaderPanel, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("BilEksport Danmark");
		titleHeaderPanel.add(lblNewLabel);
	}
	
	private void goOrderMenu() {
		maingui.switchToOrderMenu();
	}	
}