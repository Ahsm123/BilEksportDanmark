package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.OrderCtrl;
import gui.supers.GUIPanel;
import model.database.CarDB;
import model.database.CustomerDB;
import model.database.InvoiceDB;
import model.database.OrderDB;
import model.exceptions.DataAccessException;

import java.awt.*;
import java.sql.SQLException;

public class OrderMenu extends GUIPanel {

    private static final long serialVersionUID = 1L;

    public OrderMenu() {
    	super(450, 300);
 
        createTitlePanel();
        createButtonsPanel();        
    }
    
    private void createTitlePanel() {
    	JPanel titlePanel = new JPanel();
        contentPane.add(titlePanel, BorderLayout.NORTH);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("Ordre menu");
        titlePanel.add(lblNewLabel);
    }
    
    private void createButtonsPanel() {
    	JPanel buttonsPanel = new JPanel();
        contentPane.add(buttonsPanel, BorderLayout.CENTER);
        buttonsPanel.setLayout(new GridLayout(2, 2));

        JPanel createOrderPanel = new JPanel();
        buttonsPanel.add(createOrderPanel);
        createOrderPanel.setLayout(new CardLayout(0, 0));

        JButton btnOrderCreate = new JButton("Opret ordre");
        btnOrderCreate.addActionListener(e -> {
			try {
				showCustomerPopUp();
			} catch (DataAccessException | SQLException e1) {
				e1.printStackTrace();
			}
		});
        createOrderPanel.add(btnOrderCreate, "name_254181741858900");

        JPanel deleteOrderPanel = new JPanel();
        buttonsPanel.add(deleteOrderPanel);
        deleteOrderPanel.setLayout(new CardLayout(0, 0));

        JButton btnDeleteOrder = new JButton("Slet ordre");
        //btnDeleteOrder.addActionListener(e -> deleteOrder());
        deleteOrderPanel.add(btnDeleteOrder, "name_254194213853500");

        JPanel goBackPanel = new JPanel();
        buttonsPanel.add(goBackPanel);
        goBackPanel.setLayout(new BorderLayout(0, 0));

        JButton btnBack = new JButton("Gå tilbage");
        btnBack.addActionListener(e -> cancel());
        goBackPanel.add(btnBack, BorderLayout.CENTER);

        JButton btnOnlineOrder = new JButton("Online ordre");
        //btnOnlineOrder.addActionListener(e -> onlineOrder());
        buttonsPanel.add(btnOnlineOrder);
    }
    
    private void showCustomerPopUp() throws DataAccessException, SQLException {
    	maingui.switchToJDialog(new CustomerPopUp(new OrderCtrl(new OrderDB(), new CustomerDB(), new CarDB(), new InvoiceDB())), true);
    }
}