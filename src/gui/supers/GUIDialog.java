package gui.supers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Main;

public abstract class GUIDialog extends JDialog {
	protected static final long serialVersionUID = 1L;
	protected static final int PADDING = 5;
	
	protected Main maingui;
	protected JPanel contentPane;
	
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;
	
	public GUIDialog(int width, int height) {
		maingui = Main.getInstance();
		
		FRAME_WIDTH = width;
		FRAME_HEIGHT = height;
		
		createFrame();
		createContentPane();
		createFooter();
	}
	
	private void createFrame() {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    private void createContentPane() {
    	contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
    }
    
    private void createFooter() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Annuller");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> cancel());
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(e -> confirm());
		buttonPanel.add(okButton);
	}
    
    protected abstract void confirm();
    
    private void cancel() {
    	maingui.goBack();
    }
    
    protected void showErrorPopup(String e) {
    	JOptionPane.showMessageDialog(null, e, "Fejl", JOptionPane.PLAIN_MESSAGE);
    }
}