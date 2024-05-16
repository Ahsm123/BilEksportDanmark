package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JDialog;

import controller.CustomerCtrl;
import controller.OrderCtrl;

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
		
        // Controllers are instantiated here or passed from outside
        customerCtrl = new CustomerCtrl();
        try {
			orderCtrl = new OrderCtrl();
		} catch (SQLException e) {
			
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
		
		switchFrameTo(popUp);
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
	
	private void switchFrameTo(Window window) {
		currentFrame.setVisible(false);
		currentFrame = window;
		currentFrame.setVisible(true);
		windowStack.add(currentFrame);
	}

}