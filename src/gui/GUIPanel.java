package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class GUIPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private int FRAME_WIDTH = 450;
	private int FRAME_HEIGHT = 300;
	
	protected static final int PADDING = 5;
	protected Main maingui;
	protected JPanel contentPane;
	
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
}