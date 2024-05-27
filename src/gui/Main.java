package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JDialog;

import controller.CustomerCtrl;
import controller.OrderCtrl;
import model.Order;
import model.database.CarDB;
import model.database.CustomerDB;
import model.database.InvoiceDB;
import model.database.OrderDB;
import model.exceptions.DataAccessException;

public class Main {	
	private Window currentFrame;

	private LinkedList<Window> windowStack;
	
	private static Main instance;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main.getInstance().initialize();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Main getInstance() {
		if(instance == null) {
			instance = new Main();
		}
		return instance;
	}
	
	private Main() {
		windowStack = new LinkedList<>();
        
	}
	
	public void initialize() {
		JFrame mainPage = new MainPage();
		mainPage.setVisible(true);
		currentFrame = mainPage;
		windowStack.add(currentFrame);
	}

	public void goBack() {
		if(windowStack.size() > 1) {
			windowStack.removeLast();
			currentFrame.dispose();
			
			currentFrame = windowStack.getLast();
			currentFrame.setVisible(true);
		}
	}
	
	public void resetToMainPage() {
		while(windowStack.size() > 1) {
			goBack();
		}
	}
	
	public void switchFrameTo(JFrame jframe, boolean hidePrevious) {
		if(hidePrevious) {
			currentFrame.setVisible(false);
		}
		currentFrame = jframe;
		currentFrame.setVisible(true);
		windowStack.add(currentFrame);
	}
	
	public void switchToJDialog(JDialog dialog, boolean hidePrevious) {
		if(hidePrevious) {
			currentFrame.setVisible(false);
		}
		currentFrame = dialog;
		windowStack.add(currentFrame);
		
		dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
	}
}