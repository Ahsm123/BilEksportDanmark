package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import model.Copy;
import model.Order;

public class OrderPrint extends GUIPanel {
	private static final long serialVersionUID = 1L;
	private DecimalFormat formatter = new DecimalFormat("0.00");

	private JPanel mainPanel;	
	private JPanel centerOfPanes;
	private JScrollPane orderCopiesPane;
	
	public OrderPrint(Order order) {
		super(450, 800);
		
		createMainPanel();
		addCopyPanels(order);
		createHeader(order);
		createFooter(order);	
	}
	
	private void createMainPanel() {
		JPanel paddingPanel = new JPanel();
		contentPane.add(paddingPanel);
		paddingPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		paddingPanel.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		this.mainPanel = mainPanel;
	}
	
	private void addCopyPanels(Order order) {
	    orderCopiesPane = new JScrollPane();
	    orderCopiesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    orderCopiesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    mainPanel.add(orderCopiesPane, BorderLayout.CENTER);
	    
	    centerOfPanes = new JPanel();
	    centerOfPanes.setLayout(new BoxLayout(centerOfPanes, BoxLayout.Y_AXIS));
	    orderCopiesPane.setViewportView(centerOfPanes);
	    
	    for(Copy copy : order.getCopies()) {
	        addCopy(copy.getPrice(), copy.getVin());
	    }
	}
	
	private void addCopy(double price, String carVin) {
		JPanel panel_5 = new JPanel();
		centerOfPanes.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblProductName = new JLabel(carVin);
		lblProductName.setBounds(0, 0, 211, 35);
		panel_5.add(lblProductName);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(211, 0, 211, 35);
		panel_5.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrice = new JLabel(formatter.format(price));
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblPrice);
	}
	
	private void createHeader(Order order) {
		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel orderMiscInfoPanel = new JPanel();
		headerPanel.add(orderMiscInfoPanel);
		orderMiscInfoPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel customerInfoPanel = new JPanel();
		orderMiscInfoPanel.add(customerInfoPanel);
		customerInfoPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblName = new JLabel(order.getCustomer().getName());
		customerInfoPanel.add(lblName);
		
		JLabel lblCustomerType = new JLabel(order.getCustomer().getCustomerType());
		customerInfoPanel.add(lblCustomerType);
		
		JPanel orderInfoPanel = new JPanel();
		orderMiscInfoPanel.add(orderInfoPanel);
		orderInfoPanel.setLayout(new GridLayout(0, 1, 0, 5));
	
		JLabel lblOrderID = new JLabel("Ordre nr: " + order.getId());
		orderInfoPanel.add(lblOrderID);
		
		JLabel lblDate = new JLabel("Dato: " + order.getDate());
		orderInfoPanel.add(lblDate);
		
		JPanel copyDetailsPanel = new JPanel();
		headerPanel.add(copyDetailsPanel);
		copyDetailsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblProductNameStatic = new JLabel("Vin nummer");
		copyDetailsPanel.add(lblProductNameStatic);
		
		JPanel pricePaddingPanel = new JPanel();
		copyDetailsPanel.add(pricePaddingPanel);
		pricePaddingPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPriceStatic = new JLabel("Pris");
		pricePaddingPanel.add(lblPriceStatic);
	}
	
	private void createFooter(Order order) {
		JPanel footerPanel = new JPanel();
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel totalPanel = new JPanel();
		footerPanel.add(totalPanel);
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		
		JPanel totalPricePanel = new JPanel();
		totalPanel.add(totalPricePanel);
		totalPricePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel totalPriceAndTextPanel = new JPanel();
		totalPricePanel.add(totalPriceAndTextPanel);
		totalPriceAndTextPanel.setLayout(new BoxLayout(totalPriceAndTextPanel, BoxLayout.X_AXIS));
		
		JPanel totalPanelText = new JPanel();
		FlowLayout fl_totalPanelText = (FlowLayout) totalPanelText.getLayout();
		fl_totalPanelText.setAlignment(FlowLayout.LEFT);
		fl_totalPanelText.setHgap(40);
		totalPriceAndTextPanel.add(totalPanelText);
		
		JLabel lblTotalStatic = new JLabel("Total");
		totalPanelText.add(lblTotalStatic);
		
		JPanel totalPanelValue = new JPanel();
		FlowLayout fl_totalPanelValue = (FlowLayout) totalPanelValue.getLayout();
		fl_totalPanelValue.setHgap(40);
		fl_totalPanelValue.setAlignment(FlowLayout.RIGHT);
		totalPriceAndTextPanel.add(totalPanelValue);
		
		JLabel lblTotal = new JLabel(formatter.format(order.getTotalPrice()) + " DKK");
		totalPanelValue.add(lblTotal);
		
		JPanel confirmationPanel = new JPanel();
		footerPanel.add(confirmationPanel);
		
		JButton btnOrderCancel = new JButton("Print");
		confirmationPanel.add(btnOrderCancel);
		
		JButton btnOrderConfirm = new JButton("Ok");
		btnOrderConfirm.addActionListener(e ->confirm());
		confirmationPanel.add(btnOrderConfirm);
	}
	
	private void confirm() {
		maingui.resetToMainPage();
	}
}