package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OrderMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private Main maingui;
    private JPanel contentPane;

    public OrderMenu() {
        maingui = Main.getInstance();

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
        panel_1.setLayout(new GridLayout(2, 2));

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new CardLayout(0, 0));

        JButton btnOrderCreate = new JButton("Opret ordre");
        btnOrderCreate.addActionListener(e -> showCustomerPopUp());
        panel_2.add(btnOrderCreate, "name_254181741858900");

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        panel_3.setLayout(new CardLayout(0, 0));

        JButton btnDeleteOrder = new JButton("Slet ordre");
        btnDeleteOrder.addActionListener(e -> deleteOrder());
        panel_3.add(btnDeleteOrder, "name_254194213853500");

        JPanel panel_4 = new JPanel();
        panel_1.add(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        JButton btnBack = new JButton("Gå tilbage");
        btnBack.addActionListener(e -> goBack());
        panel_4.add(btnBack, BorderLayout.CENTER);

        JButton btnOnlineOrder = new JButton("Online ordre");
        panel_1.add(btnOnlineOrder);
    }

    private void goBack() {
        maingui.goBack();
    }

    private void showCustomerPopUp() {
        maingui.switchToPopUp();
    }

    private void deleteOrder() {
        // Delete order her
    }
}