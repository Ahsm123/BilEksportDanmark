package gui;

import javax.swing.*;
import java.awt.*;

public class CalculateCarMenu extends GUIPanel {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
        
    public CalculateCarMenu() {
    	super(700, 300);
    	init();
        createMainPanel();
        createDetailsPanel();
        createFooter();
        createOrderOverviewPanel();
    }
    
    private void init() {
    	maingui = Main.getInstance();
    }

    private void createMainPanel() {
    	mainPanel = new JPanel();
        contentPane.add(mainPanel);
        mainPanel.setLayout(new BorderLayout(0, 0));
    }
    
    private void createDetailsPanel() {
    	 JPanel carDetailsPanel = new JPanel();
         mainPanel.add(carDetailsPanel, BorderLayout.WEST);
         carDetailsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));

         JPanel inputFieldPanel = new JPanel();
         carDetailsPanel.add(inputFieldPanel);
         inputFieldPanel.setLayout(new GridLayout(0, 1, 0, 0));

         JPanel vinInputPanel = new JPanel();
         inputFieldPanel.add(vinInputPanel);
         vinInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JLabel lblVin = new JLabel("VIN");
         vinInputPanel.add(lblVin);

         JTextField vinField = new JTextField();
         vinField.setColumns(10);
         vinInputPanel.add(vinField);

         JPanel searchLicensePlate = new JPanel();
         inputFieldPanel.add(searchLicensePlate);
         searchLicensePlate.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JButton btnSearchLicensePlate = new JButton("Søg");
         btnSearchLicensePlate.addActionListener(e -> searchCar(vinField.getText()));
         searchLicensePlate.add(btnSearchLicensePlate);
    }
    
    private void createOrderOverviewPanel() {
    }
    
    private void createFooter() {
        JPanel footerPanel = new JPanel();
        contentPane.add(footerPanel, BorderLayout.SOUTH);
        footerPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel footerPanelInner = new JPanel();
        footerPanel.add(footerPanelInner, BorderLayout.EAST);
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(e -> cancel());
        footerPanelInner.add(btnCancel);
        
        JButton btnConfirm = new JButton("Bekræft");
        btnConfirm.addActionListener(e -> confirm());
        footerPanelInner.add(btnConfirm);
        
        JPanel carInfoPanel = new JPanel();
        contentPane.add(carInfoPanel, BorderLayout.EAST);
        carInfoPanel.setLayout(new BorderLayout(0, 0));
    }

    private void cancel() {
        maingui.goBack();
    }

    private void confirm() {
        
    }
    
    private void searchCar(String vin) {
    	
    }

    
}