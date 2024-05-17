package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JDialog;

import controller.CustomerCtrl;
import controller.OrderCtrl;
import model.database.CustomerDB;
import model.database.DataAccessException;

public class Main {	
	private Window currentFrame;
	
	private CustomerCtrl customerCtrl;
	private OrderCtrl orderCtrl;
	
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
	
	public static synchronized Main getInstance() {
		if(instance == null) {
			instance = new Main();
		}
		return instance;
	}
	
	private Main() {
		windowStack = new LinkedList<>();
		
        
        try {
			orderCtrl = new OrderCtrl();
			customerCtrl = new CustomerCtrl(new CustomerDB());
		} 
        catch (SQLException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void switchToOrderMenu() {
		JFrame orderMenu = new OrderMenu();
		
		switchFrameTo(orderMenu);
	}
	
	public void switchToPopUp() {
		JDialog popUp = new PopUp(orderCtrl, customerCtrl);
		
		switchToJDialog(popUp);
	}
	
	public void switchToOrderInfo() {	
		JFrame orderInfo = new OrderInfo(orderCtrl);
		
		switchFrameTo(orderInfo);
	}
	
	public void createOrderPrint() {
		JFrame orderPrint = new OrderPrint(orderCtrl.getOrder());
		
		switchFrameTo(orderPrint);
	}
	
	public void resetToMainPage() {
		while(windowStack.size() > 1) {
			goBack();
		}
	}
	
	private void switchFrameTo(JFrame jframe) {
		currentFrame.setVisible(false);
		currentFrame = jframe;
		currentFrame.setVisible(true);
		windowStack.add(currentFrame);
	}
	
	private void switchToJDialog(JDialog dialog) {
		currentFrame.setVisible(false);
		currentFrame = dialog;
		windowStack.add(currentFrame);
		
		dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
	}
}