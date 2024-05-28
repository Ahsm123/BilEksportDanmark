package gui.car;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SellerCtrl;
import gui.supers.GUIPanel;
import model.Seller;
import model.database.SellerDB;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.DataAccessException;

import javax.swing.BoxLayout;

public class AddSeller extends GUIPanel {

	JPanel mainPanel;
	
	SellerCtrl sellerCtrl;
	
	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField phoneField;
	private JTextField mailField;
	private JTextField linkField;

	public AddSeller() {
		super(400, 400);

		try {
			this.sellerCtrl = new SellerCtrl(new SellerDB());
			
			createMainPanel();
			createFooter();
			createInputPanel();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createMainPanel() {
		mainPanel = new JPanel();
		contentPane.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
	}

	private void createFooter() {
		JPanel buttonsPanel = new JPanel();

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Annuller");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> cancel());
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("Gem");
		okButton.setActionCommand("Gem");
		okButton.addActionListener(e -> saveSeller());
		buttonsPanel.add(okButton);
	}

	private void createInputPanel() {
		JPanel carDetailsPanel = new JPanel();
		mainPanel.add(carDetailsPanel, BorderLayout.CENTER);
		carDetailsPanel.setLayout(new BorderLayout(0, 0));

		JPanel inputFieldPanel = new JPanel();
		carDetailsPanel.add(inputFieldPanel);
		inputFieldPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel fnameInputPanel = new JPanel();
		inputFieldPanel.add(fnameInputPanel);
		fnameInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

		JLabel lblfname = new JLabel("Fornavn");
		fnameInputPanel.add(lblfname);
		
		fnameField = new JTextField();
		fnameField.setColumns(12);
		fnameInputPanel.add(fnameField);
		
		JPanel lnameInputPanel = new JPanel();
		inputFieldPanel.add(lnameInputPanel);
		lnameInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lbllname = new JLabel("Efternavn");
		lnameInputPanel.add(lbllname);
		
		lnameField = new JTextField();
		lnameField.setColumns(12);
		lnameInputPanel.add(lnameField);
		
		JPanel phoneInputPanel = new JPanel();
		inputFieldPanel.add(phoneInputPanel);
		phoneInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblPhone = new JLabel("Telefonnummer");
		phoneInputPanel.add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setColumns(12);
		phoneInputPanel.add(phoneField);
		
		JPanel mailInputPanel = new JPanel();
		inputFieldPanel.add(mailInputPanel);
		mailInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblMail = new JLabel("Mail");
		mailInputPanel.add(lblMail);
		
		mailField = new JTextField();
		mailField.setColumns(12);
		mailInputPanel.add(mailField);
		
		JPanel linkInputPanel = new JPanel();
		inputFieldPanel.add(linkInputPanel);
		linkInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblLink = new JLabel("Link");
		linkInputPanel.add(lblLink);
		
		linkField = new JTextField();
		linkField.setColumns(12);
		linkInputPanel.add(linkField);
	}

	private void saveSeller() {
		try {
			sellerCtrl.saveSeller(fnameField.getText() + " " + lnameField.getText(), phoneField.getText(), mailField.getText(), linkField.getText());
			cancel();
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}


}
