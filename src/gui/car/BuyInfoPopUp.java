package gui.car;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CalculateCarCtrl;
import gui.supers.GUIDialog;

public class BuyInfoPopUp extends GUIDialog {
	private static final long serialVersionUID = 1L;
	private CalculateCarCtrl calculateCarCtrl;

	public BuyInfoPopUp(CalculateCarCtrl calculateCarCtrl, double price) {
		super(300, 150);
		this.calculateCarCtrl = calculateCarCtrl;
		createContent(price);
	}
	private void createContent(double price) {
    	JLabel lblPhone = new JLabel("Beregnet maks pris: " + price);
        contentPane.add(lblPhone);
        
        JButton saveButton = new JButton("Gem");
		saveButton.addActionListener(e -> save());
		buttonsPanel.add(saveButton);
    }
	
	private void save() {
		maingui.switchToJDialog(new FindSellerPopUp(calculateCarCtrl), true);
	}
}