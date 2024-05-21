package gui;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.database.DBConnection;
import model.database.DataAccessException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 5;
	
	private Main maingui;
	
	private JPanel contentPane;

	public MainPage() {
		init();
		createFrame();
		createContentPane();
		createButtonsPanel();
		createHeaderPanel();
	}

	private void init() {
		try {
			DBConnection.getInstance();
			maingui = Main.getInstance();
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}
	
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
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