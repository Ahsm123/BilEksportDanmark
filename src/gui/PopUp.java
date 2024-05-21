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
import model.exceptions.DataAccessException;
import controller.CustomerCtrl;

public class PopUp extends JDialog {
	private Main maingui;
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 5;
	private OrderCtrl orderCtrl;
	private CustomerCtrl customerCtrl;
	
	private JPanel contentPanel = new JPanel();
	private JTextField textField;

	
	public PopUp(OrderCtrl orderCtrl, CustomerCtrl customerCtrl) {
        this.maingui = Main.getInstance();
        this.orderCtrl = orderCtrl;
        this.customerCtrl = customerCtrl;
        
        initialize();
        
        createContentPanel();
        createButtonsPanel();
    }

    private void initialize() {
        setModal(true);
        setBounds(100, 100, 450, 300);        
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblPhone = new JLabel("Telefonnummer");
        contentPanel.add(lblPhone);

        textField = new JTextField();
        textField.setText("12345678");
        textField.setColumns(10);
        contentPanel.add(textField);
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
	    try {
	    	String phoneNo = textField.getText();
			if(customerCtrl.doesCustomerExist(phoneNo)) {
				orderCtrl.createOrder(phoneNo, 1);
				maingui.switchToOrderInfo();
			}
			else {
				JOptionPane.showMessageDialog(null, "Kunde eksisterer ikke", "Fejl", JOptionPane.PLAIN_MESSAGE);
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