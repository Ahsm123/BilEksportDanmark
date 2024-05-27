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
	private JTextField textFieldSalesPrice;
	private JTextField textFieldPurchasePrice;

	public PricePopUp(CalculateCarCtrl calculateCarCtrl) {
		initialize(calculateCarCtrl);

		createContentPanel();
		createButtonsPanel();
	}

	private void initialize(CalculateCarCtrl calculateCarCtrl) {
		this.maingui = Main.getInstance();
		this.calculateCarCtrl = calculateCarCtrl;
		setModal(true);
		setBounds(100, 100, 450, 200);        
	}

	private void createContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblSalesPrice = new JLabel("Salgspris");
		panel.add(lblSalesPrice);

		textFieldSalesPrice = new JTextField();
		textFieldSalesPrice.setColumns(10);
		panel.add(textFieldSalesPrice);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblPurchasePrice = new JLabel("Købspris");
		panel_1.add(lblPurchasePrice);

		textFieldPurchasePrice = new JTextField();
		textFieldPurchasePrice.setColumns(10);
		panel_1.add(textFieldPurchasePrice);
	}

	private void createButtonsPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Annuller");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> cancel());
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(e -> createOrder());
		buttonPanel.add(okButton);
	}

	private void createOrder() {
		
	}

	private void cancel() {
		maingui.goBack();
	}
}