package gui.car;

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
import gui.supers.GUIDialog;
import model.Copy;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class PricePopUp extends GUIDialog {
	private static final long serialVersionUID = 1L;
	
	private CalculateCarCtrl calculateCarCtrl;
	
	private Copy copy;

	private JTextField textFieldSalesPrice;

	public PricePopUp(CalculateCarCtrl calculateCarCtrl, Copy copy) {
		super(300, 150);
		initialize(calculateCarCtrl, copy);

		createContent();
		
	}

	private void initialize(CalculateCarCtrl calculateCarCtrl, Copy copy) {
		this.copy = copy;
		this.calculateCarCtrl = calculateCarCtrl;
		setModal(true);
		setBounds(100, 100, 450, 150);        
	}

	private void createContent() {
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblSalesPrice = new JLabel("Salgspris");
		panel_2.add(lblSalesPrice);
		
		textFieldSalesPrice = new JTextField();
		textFieldSalesPrice.setColumns(10);
		panel_2.add(textFieldSalesPrice);
	}

	private void calculateCar(Copy copy) {
		if(textFieldSalesPrice.getText() == ""){
			JOptionPane.showMessageDialog(null, "Mangler input", "Fejl", JOptionPane.PLAIN_MESSAGE);
		}
		else {
			try {
				double salesPrice = Double.parseDouble(textFieldSalesPrice.getText());
				
				double price = calculateCarCtrl.calculateOffer(copy, salesPrice);
			
				maingui.switchToJDialog(new BuyInfoPopUp(calculateCarCtrl, price), true);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Sørg for kun tal er i inputfæltet", "Fejl", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	@Override
	protected void confirm() {
		calculateCar(copy);
	}
}