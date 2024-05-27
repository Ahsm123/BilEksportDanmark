package gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.CalculateCarCtrl;
import controller.OrderCtrl;
import model.Copy;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class PricePopUp extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 5;

	private CalculateCarCtrl calculateCarCtrl;
	private Main maingui;

	private JPanel contentPanel = new JPanel();
	private JTextField textField;

	public PricePopUp(CalculateCarCtrl calculateCarCtrl, Copy copy) {
		initialize(calculateCarCtrl);

		createContentPanel();
		createButtonsPanel(copy);
	}

	private void initialize(CalculateCarCtrl calculateCarCtrl) {
		this.maingui = Main.getInstance();
		this.calculateCarCtrl = calculateCarCtrl;
		setModal(true);
		setBounds(100, 100, 450, 150);        
	}

	private void createContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblSalesPrice = new JLabel("Salgspris");
		panel_2.add(lblSalesPrice);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_2.add(textField);
	}

	private void createButtonsPanel(Copy copy) {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Annuller");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> cancel());
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(e -> calculateCar(copy));
		buttonPanel.add(okButton);
	}
	
	private void calculateCar(Copy copy) {
		if(textFieldSalesPrice.getText() == ""){
			JOptionPane.showMessageDialog(null, "Mangler input", "Fejl", JOptionPane.PLAIN_MESSAGE);
		}
		else {
			try {
				double salesPrice = Double.parseDouble(textFieldSalesPrice.getText());
				
				double price = calculateCarCtrl.CalculateOffer(copy, salesPrice);
				JOptionPane.showMessageDialog(null, "Beregnet maks pris: " + price, "Success", JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Sørg for kun tal er i inputfæltet", "Fejl", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void cancel() {
		maingui.goBack();
	}
}