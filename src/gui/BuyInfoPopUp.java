package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.supers.GUIDialog;

public class BuyInfoPopUp extends GUIDialog {
	private static final long serialVersionUID = 1L;

	public BuyInfoPopUp(double price) {
		super(300, 150);
		createContent(price);
	}
	private void createContent(double price) {
    	JLabel lblPhone = new JLabel("Beregnet maks pris: " + price);
        contentPane.add(lblPhone);
        
        JButton cancelButton = new JButton("Gem");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> save());
		buttonsPanel.add(cancelButton);
    }
	
	private void save() {
		
	}
}