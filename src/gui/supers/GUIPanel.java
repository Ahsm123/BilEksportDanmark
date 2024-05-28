package gui.supers;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Main;

public abstract class GUIPanel extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected static final int PADDING = 5;
	
	protected Main maingui;
	protected JPanel contentPane;
	
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;
	
	public GUIPanel(int width, int height) {
		maingui = Main.getInstance();
		
		FRAME_WIDTH = width;
		FRAME_HEIGHT = height;
		
		createFrame();
		createContentPane();
	}
	
	private void createFrame() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    private void createContentPane() {
    	this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));
    }
    
    protected void showErrorPopup(String e) {
    	JOptionPane.showMessageDialog(null, e, "Fejl", JOptionPane.PLAIN_MESSAGE);
    }
    
    protected void cancel() {
    	maingui.goBack();
    }
}