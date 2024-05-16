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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.OrderCtrl;
import model.database.DataAccessException;
import controller.CustomerCtrl;

public class PopUp extends JDialog {
	private Main maingui;
	private static final long serialVersionUID = 1L;
	private OrderCtrl orderCtrl;
	private CustomerCtrl customerCtrl;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public PopUp(OrderCtrl orderCtrl, CustomerCtrl customerCtrl) {
		maingui = Main.getInstance();
		this.orderCtrl = orderCtrl;
		this.customerCtrl = customerCtrl;
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblPhone = new JLabel("Telefonnummer");
			contentPanel.add(lblPhone);
		}
		{
			textField = new JTextField();
			textField.setText("12345678");
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createOrder();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
		}
	}
	
	private void createOrder() {
	    try {
	    	String phoneNo = textField.getText();
			if(customerCtrl.doesCustomerExist(phoneNo)) {
				orderCtrl.createOrder(phoneNo);
				maingui.switchToOrderInfo();
			}
			else {
				if(customerCtrl.findCustomer(textField.getText()) == null) {

			    JOptionPane.showMessageDialog(null, "Kunde eksisterer ikke", "Fejl", JOptionPane.PLAIN_MESSAGE);
				}
			}
		} 
	    catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, "Keyboard ikke supported", "Fejl", JOptionPane.PLAIN_MESSAGE);
		} 
	    catch (DataAccessException e) {
			JOptionPane.showMessageDialog(null, "Database fejl", "Fejl", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void cancel() {
		maingui.goBack();
	}
}