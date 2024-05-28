package gui;

import javax.swing.*;

import controller.CalculateCarCtrl;
import controller.CarCtrl;
import gui.supers.GUIPanel;
import model.Copy;
import model.database.CarDB;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.DataAccessException;

import java.awt.*;

public class CalculateCarMenu extends GUIPanel {
	private static final long serialVersionUID = 1L;
	
	private Copy currentlySelected;
	private JPanel mainPanel;
	private JPanel carInfoPanel;

    private CalculateCarCtrl calculateCarCtrl;
	
    public CalculateCarMenu() {
    	super(900, 300);
    	
    	try {
    		init();
    		createMainPanel();
    		createInputPanel();
            createFooter();
    	}
    	catch (DataAccessException e){
    		System.out.println(e);
    	}
    }
    
    private void init() throws DataAccessException {
    	maingui = Main.getInstance();
    	calculateCarCtrl = new CalculateCarCtrl();
    }

    private void createMainPanel() {
    	mainPanel = new JPanel();
        contentPane.add(mainPanel);
        mainPanel.setLayout(new BorderLayout(0, 0));
    }
    
    private void createInputPanel() {
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
         vinField.setText("22");
         vinField.setColumns(12);
         vinInputPanel.add(vinField);

         JPanel searchLicensePlate = new JPanel();
         inputFieldPanel.add(searchLicensePlate);
         searchLicensePlate.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JButton btnSearchLicensePlate = new JButton("Søg");
         btnSearchLicensePlate.addActionListener(e -> {
			try {
				searchCar(vinField.getText());
			} catch (CarDoesNotMeetRequirementsException e1) {
				e1.printStackTrace();
			}
		});
         searchLicensePlate.add(btnSearchLicensePlate);
    }
    
    private void createCarOverviewPanel(Copy copy) {
    	if(carInfoPanel != null) {
    		carInfoPanel.removeAll();
    		mainPanel.remove(carInfoPanel);
    		revalidate();
    		repaint();
    	}
    	
    	carInfoPanel = new JPanel();
        mainPanel.add(carInfoPanel, BorderLayout.CENTER);
        carInfoPanel.setLayout(new GridLayout(4, 4, 0, 0));
        
        JLabel lblVinLabel = new JLabel("VIN: " + copy.getVin());
        lblVinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblVinLabel);
        
        JLabel lblManuLabel = new JLabel("Fabrikant: " + copy.getManufacturer());
        lblManuLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblManuLabel);
        
        JLabel lblModelLabel = new JLabel("Model: " + copy.getModel());
        lblModelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblModelLabel);
        
        JLabel lblColorLabel = new JLabel("Color: " + copy.getColor());
        lblColorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblColorLabel);
        
        
        JLabel lblHPLabel = new JLabel("HP: " + copy.getHp());
        lblHPLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblHPLabel);
        
        JLabel lblAccelerationLabel = new JLabel("Acceleration: " + copy.getAcceleration());
        lblAccelerationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblAccelerationLabel);
        
        JLabel lblTopSpeedLabel = new JLabel("Top fart: " + copy.getTopSpeed());
        lblTopSpeedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblTopSpeedLabel);
        
        JLabel lblCo2Label = new JLabel("CO2: " + copy.getCo2Emission());
        lblCo2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblCo2Label);
        
        
        JLabel lblYearLabel = new JLabel("År: " + copy.getYear());
        lblYearLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblYearLabel);
        
        JLabel lblInspectedLabel = new JLabel("Inspiceret: " + copy.isInspected());
        lblInspectedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblInspectedLabel);
        
        JLabel lblTaxReturnLabel = new JLabel("Skat: " + copy.isTaxReturn());
        lblTaxReturnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblTaxReturnLabel);
        
        JLabel lblStateLabel = new JLabel("State: " + copy.getState());
        lblStateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblStateLabel);
        
        
        JLabel lblMilageLabel = new JLabel("K/L: " + copy.getMilage());
        lblMilageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblMilageLabel);
        
        JLabel lblKilometerLabel = new JLabel("Kilometer: " + copy.getKilometer());
        lblKilometerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblKilometerLabel);
        
        JLabel lblPriceLabel = new JLabel("Pris: " + copy.getPurchasePrice());
        lblPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblPriceLabel);
        
        JLabel lblRegistrationFeeLabel = new JLabel("Reg afgift: " +  copy.getRegistrationFee());
        lblRegistrationFeeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carInfoPanel.add(lblRegistrationFeeLabel);     
        
        mainPanel.revalidate();
		mainPanel.repaint();
    }
    
    private void createFooter() {
        JPanel footerPanel = new JPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        footerPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel footerPanelInner = new JPanel();
        footerPanel.add(footerPanelInner, BorderLayout.EAST);
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(e -> cancel());
        footerPanelInner.add(btnCancel);
        
        JButton btnConfirm = new JButton("Bekræft");
        btnConfirm.addActionListener(e -> confirm());
        footerPanelInner.add(btnConfirm);
    }

    private void confirm() {
    	if(currentlySelected != null) {
    		maingui.switchToJDialog(new PricePopUp(calculateCarCtrl, currentlySelected), false);
    	}
    	else {
    		showErrorPopup("Ingen bil valgt");
    	}	
    }
    
    private void searchCar(String vin) throws CarDoesNotMeetRequirementsException {
    	currentlySelected = calculateCarCtrl.importCopy(vin);
    	if(currentlySelected != null) {
    		createCarOverviewPanel(currentlySelected);
    	}
    	else {
    		showErrorPopup("Bil ikke fundet");
    	}
    }
}