package gui;

import controller.CarCtrl;
import controller.OrderCtrl;
import model.Copy;
import model.EmptyOrderException;
import model.database.CarAlreadySoldException;
import model.database.CarDB;
import model.database.DataAccessException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderInfo extends JFrame {
    private static final long serialVersionUID = 1L;

    private final OrderCtrl orderCtrl;
    private final Main maingui;
    private boolean threadNeedsToRun = true;

    private JPanel contentPane;
    private JTextField barcodeField;
    private JPanel centerOfPanels;
    private LinkedList<CopyPanel> carPanels;
    private CarCtrl carCtrl;
    private CheckIfSoldThread thread;

    public OrderInfo(OrderCtrl orderCtrl) {
        maingui = Main.getInstance();
        this.orderCtrl = orderCtrl;
        try {
            this.carCtrl = new CarCtrl(new CarDB());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        this.carPanels = new LinkedList<>();
        thread = new CheckIfSoldThread(orderCtrl, this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel_1.add(panel, BorderLayout.WEST);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel_5 = new JPanel();
        panel.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_3_1 = new JPanel();
        panel_5.add(panel_3_1);
        panel_3_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JLabel lblBarcode = new JLabel("Vin");
        panel_3_1.add(lblBarcode);

        barcodeField = new JTextField();
        barcodeField.setText("123456789abcdefgh");
        barcodeField.setColumns(10);
        panel_3_1.add(barcodeField);

        JPanel panel_3_2 = new JPanel();
        panel_5.add(panel_3_2);
        panel_3_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnConfirm = new JButton("Tilføj");
        btnConfirm.addActionListener(e -> addCar(barcodeField.getText()));
        panel_3_2.add(btnConfirm);

        JPanel panel_2_1 = new JPanel();
        panel_1.add(panel_2_1);
        panel_2_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_4 = new JPanel();
        panel_2_1.add(panel_4, BorderLayout.NORTH);

        JLabel lblOrderInfo = new JLabel("Ordreoversigt");
        panel_4.add(lblOrderInfo);

        JPanel panel_2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
        flowLayout.setHgap(100);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        contentPane.add(panel_2, BorderLayout.SOUTH);

        JButton btnOrderCancel = new JButton("Annuller");
        btnOrderCancel.addActionListener(e -> cancel());
        panel_2.add(btnOrderCancel);

        JButton btnOrderConfirm = new JButton("Bekræft");
        btnOrderConfirm.addActionListener(e -> confirm());
        panel_2.add(btnOrderConfirm);

        // Setup til at have flere cars
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_2_1.add(scrollPane, BorderLayout.CENTER);

        centerOfPanels = new JPanel();
        centerOfPanels.setLayout(new BoxLayout(centerOfPanels, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(centerOfPanels);
    }

    public boolean needsToRun() {
        return threadNeedsToRun;
    }

    private void addCar(String input) {
        try {
            createCarPanel(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Fejl", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void createCarPanel(String input) throws SQLException, CarAlreadySoldException {
        try {
            if (!orderCtrl.isCopyInAnOrder(input)) {
                orderCtrl.addCopy(input);

                if (!thread.isAlive()) {
                    thread.start();
                }
                Copy copy = carCtrl.findCopy(input);

                JPanel orderlinePanel = new JPanel();

                CopyPanel cPanel = new CopyPanel(copy.getVin(), orderlinePanel);

                carPanels.add(cPanel);

                centerOfPanels.add(orderlinePanel);
                orderlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                JLabel orderlineLabel = new JLabel(copy.getVin());
                orderlinePanel.add(orderlineLabel);

                JButton btnDelete = new JButton("Fjern");
                btnDelete.setBackground(Color.YELLOW);

                btnDelete.addActionListener(e -> deletePanel(cPanel));
                orderlinePanel.add(btnDelete);

                orderlinePanel.revalidate();
            } else {
                throw new CarAlreadySoldException("Bil allerede solgt");
            }
        } catch (DataAccessException e) {
            System.out.println(e);
        }
    }

    private void deletePanel(CopyPanel panelToDelete) {
        try {
            orderCtrl.removeCopy(panelToDelete.getCopyVin());

            panelToDelete.getPanel().removeAll();
            carPanels.remove(panelToDelete);
            centerOfPanels.remove(panelToDelete.getPanel());
            revalidate();
            repaint();
        } catch (EmptyOrderException e) {
            e.printStackTrace();
        }
    }

    private void cancel() {
        threadNeedsToRun = false;

        maingui.goBack();
    }

    private void confirm() {
        try {
            threadNeedsToRun = false;
            orderCtrl.confirmOrder();
            maingui.createOrderPrint();
        } catch (SQLException | DataAccessException | EmptyOrderException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<CopyPanel> getPanels() {
        return carPanels;
    }

    public void deletedPanelAlert() {
        JOptionPane.showMessageDialog(null, "En eller flere biler på ordren er allerede solgt og er derfor blevet fjernet", "Notice", JOptionPane.PLAIN_MESSAGE);
    }

    class CheckIfSoldThread extends Thread {
        private final OrderInfo orderInfo;
        private final OrderCtrl orderCtrl;
        private static final long SLEEP_TIME = 5000;

        public CheckIfSoldThread(OrderCtrl orderCtrl, OrderInfo orderInfo) {
            this.orderCtrl = orderCtrl;
            this.orderInfo = orderInfo;
        }

        public void run() {
            while (orderInfo.needsToRun()) {
                try {
                    boolean hasDeletedPanels = false;
                    for (CopyPanel cPanel : orderInfo.getPanels()) {
                        if (orderCtrl.isCopyInAnOrder(cPanel.getCopyVin())) {
                            orderInfo.deletePanel(cPanel);
                            hasDeletedPanels = true;
                        }
                    }
                    if (hasDeletedPanels) {
                        orderInfo.deletedPanelAlert();
                    }
                    sleep(SLEEP_TIME);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}