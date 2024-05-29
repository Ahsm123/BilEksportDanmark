package gui.order;

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
import guiExceptions.*;
import controller.OrderCtrl;
import gui.Main;
import gui.TextInput;
import gui.supers.GUIDialog;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import controller.CustomerCtrl;

public class CustomerPopUp extends GUIDialog {
	private OrderCtrl orderCtrl;

	private JTextField textField;

	public CustomerPopUp(OrderCtrl orderCtrl) {
		super(300, 150);
		initialize(orderCtrl);

		createContent();
	}

	private void initialize(OrderCtrl orderCtrl) {
		this.maingui = Main.getInstance();
		this.orderCtrl = orderCtrl;
	}

	private void createContent() {
		JLabel lblPhone = new JLabel("Telefonnummer");
		contentPane.add(lblPhone);

		textField = new JTextField();
		textField.setText("12345678");
		textField.setColumns(10);
		contentPane.add(textField);

	}

    private void createOrder() {
    	TextInput textInput = new TextInput();
        try {
            
            boolean isValidPhone = textInput.phoneNumberValidator(textField.getText());
            
            
            if (isValidPhone) {
                orderCtrl.createOrder(textField.getText(), 1);
                maingui.switchFrameTo(new OrderInfo(orderCtrl), true);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Keyboard ikke supported", "Fejl", JOptionPane.PLAIN_MESSAGE);
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(null, "Database fejl", "Fejl", JOptionPane.PLAIN_MESSAGE);
        } catch (CustomerNotFound e) {
            JOptionPane.showMessageDialog(null, "Kunde eksisterer ikke", "Fejl", JOptionPane.PLAIN_MESSAGE);
        } catch (InvalidPhoneNumberException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.PLAIN_MESSAGE);
        }
    }

	@Override
	protected void confirm() {
		createOrder();
	}
}