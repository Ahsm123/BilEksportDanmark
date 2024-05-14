package gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

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
import controller.CustomerCtrl;

public class PopUp extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderCtrl orderCtrl;
	private CustomerCtrl customerCtrl;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public PopUp(OrderCtrl OrderCtrl, CustomerCtrl customerCtrl) {
		this.orderCtrl = OrderCtrl;
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
						addCustomerToOrder();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
		}
	}
	
	private void addCustomerToOrder() {
//	    if (orderCtrl.addCustomerToOrder(textField.getText())) {
//	    	new OrderInfo(orderCtrl).setVisible(true);
//	    	
//	        dispose();	       
//	    } 
//	    else {
//
//	    	if(customerCtrl.findCustomer(textField.getText()) == null) {
//
//	        JOptionPane.showMessageDialog(null, "Kunde eksisterer ikke", "Fejl", JOptionPane.PLAIN_MESSAGE);
//	    	}
//	    }
	}
	
	private void cancel() {
		new OrderMenu().setVisible(true);
		
		dispose();
	}
}