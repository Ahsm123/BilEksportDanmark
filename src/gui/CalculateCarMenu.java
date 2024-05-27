package gui;

import javax.swing.*;

import controller.CarCtrl;
import model.Copy;
import model.database.CarDB;
import model.exceptions.DataAccessException;

import java.awt.*;

public class CalculateCarMenu extends GUIPanel {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel carInfoPanel;
	private CarCtrl carCtrl;
        
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
    
    private void init() {
    	maingui = Main.getInstance();
    }

    private void createMainPanel() throws DataAccessException {
    	mainPanel = new JPanel();
    	this.carCtrl = new CarCtrl(new CarDB());
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
         vinField.setText("123456789abcdefff");
         vinField.setColumns(12);
         vinInputPanel.add(vinField);

         JPanel searchLicensePlate = new JPanel();
         inputFieldPanel.add(searchLicensePlate);
         searchLicensePlate.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JButton btnSearchLicensePlate = new JButton("Søg");
         btnSearchLicensePlate.addActionListener(e -> searchCar(vinField.getText()));
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
        
        JLabel lblRegistrationFeeLabel = new JLabel("Afgift: " +  copy.getRegistrationFee());
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

    private void cancel() {
        maingui.goBack();
    }

    private void confirm() {
        
    }
    
    private void searchCar(String vin) {
		try {
			Copy copy = carCtrl.findCopy(vin);
			createCarOverviewPanel(copy);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
    }
}