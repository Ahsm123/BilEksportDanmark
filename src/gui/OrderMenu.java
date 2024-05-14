package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import controller.CustomerCtrl;
import controller.OrderCtrl;
import model.database.DataAccessException;

public class OrderMenu extends JFrame {

	private OrderCtrl orderCtrl;
	private CustomerCtrl customerCtrl;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnBack;


	/**
	 * Create the frame.
	 */
	public OrderMenu() {
		try {
			orderCtrl = new OrderCtrl();
			customerCtrl = new CustomerCtrl();
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Ordre menu");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JButton btnOrderCreate = new JButton("Opret ordre");
		
		btnOrderCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					createOrder();
			}
		});
		panel_2.add(btnOrderCreate, "name_254181741858900");
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		JButton btnDeleteOrder = new JButton("Slet ordre");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.add(btnDeleteOrder, "name_254194213853500");
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		btnBack = new JButton("Gå tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		panel_4.add(btnBack, BorderLayout.CENTER);
		
		JButton btnOnlineOrder = new JButton("Online ordre");
		panel_1.add(btnOnlineOrder);
	}
	
	private void goBack() {
		new MainPage().setVisible(true);
		this.dispose();
	}

	private void createOrder() {		
		//orderCtrl.createOrder();
		
		new PopUp(orderCtrl, customerCtrl).setVisible(true);
		
		dispose();
	}
}