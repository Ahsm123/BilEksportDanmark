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

public class CarMenu extends GUIPanel {

    private static final long serialVersionUID = 1L;

    public CarMenu() {
    	super(450, 300);
 
        createTitlePanel();
        createButtonsPanel();        
    }
    
    private void createTitlePanel() {
    	JPanel titlePanel = new JPanel();
        contentPane.add(titlePanel, BorderLayout.NORTH);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("Bil menu");
        titlePanel.add(lblNewLabel);
    }
    
    private void createButtonsPanel() {
    	JPanel buttonsPanel = new JPanel();
        contentPane.add(buttonsPanel, BorderLayout.CENTER);
        buttonsPanel.setLayout(new GridLayout(2, 2));

        JPanel createCarPanel = new JPanel();
        buttonsPanel.add(createCarPanel);
        createCarPanel.setLayout(new CardLayout(0, 0));

        JButton btnCreateCar = new JButton("Opret bil");
        // btnCreateCar.addActionListener(e -> createCar());
        createCarPanel.add(btnCreateCar, "name_254181741858900");

        JPanel deleteCarPanel = new JPanel();
        buttonsPanel.add(deleteCarPanel);
        deleteCarPanel.setLayout(new CardLayout(0, 0));

        JButton btnDeleteCar = new JButton("Slet bil");
        //btnDeleteOrder.addActionListener(e -> deleteOrder());
        deleteCarPanel.add(btnDeleteCar, "name_254194213853500");

        JPanel goBackPanel = new JPanel();
        buttonsPanel.add(goBackPanel);
        goBackPanel.setLayout(new BorderLayout(0, 0));

        JButton btnBack = new JButton("Gå tilbage");
        btnBack.addActionListener(e -> cancel());
        goBackPanel.add(btnBack, BorderLayout.CENTER);

        JButton btnCalculateCar = new JButton("Beregn bil");
        btnCalculateCar.addActionListener(e -> goCalculateCar());
        buttonsPanel.add(btnCalculateCar);
    }

    private void goCalculateCar() {
    	maingui.switchFrameTo(new CalculateCarMenu(), true);
    }
}