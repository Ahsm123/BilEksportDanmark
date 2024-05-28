package gui.order;

import javax.swing.JPanel;

public class CopyPanel {
	private String copyVin;
	private JPanel panel;
	
	public CopyPanel(String copyVin, JPanel panel) {
		this.copyVin = copyVin;
		this.panel = panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getCopyVin() {
		return copyVin;
	}
}