package gui.car;

import java.awt.GridLayout;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CalculateCarCtrl;
import controller.SellerCtrl;
import gui.TextInput;
import gui.supers.GUIDialog;
import gui.exceptions.InvalidPhoneNumberException;
import model.Seller;
import model.database.SellerDB;
import model.exceptions.DataAccessException;

public class FindSellerPopUp extends GUIDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldInput;
	private CalculateCarCtrl calculateCarCtrl;
	
	public FindSellerPopUp(CalculateCarCtrl calculateCarCtrl) {
		super(300, 150);
		initialize(calculateCarCtrl);
		createContent();
	}

	private void initialize(CalculateCarCtrl calculateCarCtrl) {
		this.calculateCarCtrl = calculateCarCtrl;
		setModal(true);
		setBounds(100, 100, 300, 150);        
	}

	private void createContent() {
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblSalesPrice = new JLabel("Telefonnummer");
		panel_2.add(lblSalesPrice);
		
		textFieldInput = new JTextField();
		textFieldInput.setColumns(10);
		panel_2.add(textFieldInput);
		
		JButton saveButton = new JButton("Opret sælger");
		saveButton.addActionListener(e -> openCreateSeller());
		buttonsPanel.add(saveButton);
	}
	
    protected void confirm() {
    	TextInput textInput = new TextInput();
        try {
            boolean isValidPhone = textInput.phoneNumberValidator(textFieldInput.getText());
            
            if (isValidPhone) {
                Seller seller = calculateCarCtrl.findSellerFromPhone(textFieldInput.getText());
                if (seller != null) {
                    try {
                        calculateCarCtrl.saveBuyInfo(seller);
                        maingui.resetToMainPage();
                    } catch (SQLException | DataAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    showErrorPopup("Sælger er ikke i systemet. Skriv et andet telefonnummer eller tilføj sælgeren");
                }
            }
        } catch (InvalidPhoneNumberException e) {
            showErrorPopup(e.getMessage());
        }
    }
	
	private void openCreateSeller() {
		maingui.switchFrameTo(new AddSeller(), true);
	}
}
