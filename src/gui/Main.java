package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Main {	
	private Window currentWindow;

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
		currentWindow = mainPage;
		windowStack.add(currentWindow);
	}

	public void goBack() {
		if(windowStack.size() > 1) {
			windowStack.removeLast();
			currentWindow.dispose();
			
			currentWindow = windowStack.getLast();
			currentWindow.setVisible(true);
		}
	}
	
//	private void printWindows() {
//		System.out.println();
//		for(Window window : windowStack) {
//			System.out.println(window.getClass());
//		}
//	}
	
	public void resetToMainPage() {
		while(windowStack.size() > 1) {
			goBack();
		}
	}
	
	public void switchFrameTo(JFrame jframe, boolean hidePrevious) {
		if(hidePrevious) {
			currentWindow.setVisible(false);
		}
		currentWindow = jframe;
		currentWindow.setVisible(true);
		windowStack.add(currentWindow);
	}
	
	public void switchDialogTo(JDialog dialog, boolean hidePrevious) {
		if(hidePrevious) {
			currentWindow.setVisible(false);
		}
		currentWindow = dialog;
		windowStack.add(currentWindow);
		
		dialog.setModal(false);
		dialog.setVisible(true);
	}
}