package gui;

import controller.CarCtrl;
import controller.OrderCtrl;
import model.Copy;
import model.database.CarDB;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderInfo extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int PADDING = 5;

    private JPanel mainPanel;
    private JPanel contentPane;
    private JPanel centerOfPanels;
    
    private LinkedList<CopyPanel> carPanels;
    private CheckIfSoldThread thread;
    private volatile boolean threadNeedsToRun = true;
    
    private CarCtrl carCtrl;
    private OrderCtrl orderCtrl;
    private Main maingui;

    public OrderInfo(OrderCtrl orderCtrl) {
    	init(orderCtrl);
    	createFrame();
        createContentPane();
        createMainPanel();
        createDetailsPanel();
        createFooter();
        createOrderOverviewPanel();
    }
    
    private void init(OrderCtrl orderCtrl) {
    	maingui = Main.getInstance();
        this.orderCtrl = orderCtrl;
        try {
            this.carCtrl = new CarCtrl(new CarDB());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        this.carPanels = new LinkedList<>();
        thread = new CheckIfSoldThread(orderCtrl, this);
    }

    private void createFrame() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
    }
    
    private void createContentPane() {
    	contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
    }
    
    private void createMainPanel() {
    	mainPanel = new JPanel();
        contentPane.add(mainPanel);
        mainPanel.setLayout(new BorderLayout(0, 0));
    }
    
    private void createDetailsPanel() {
    	 JPanel carDetailsPanel = new JPanel();
         mainPanel.add(carDetailsPanel, BorderLayout.WEST);
         carDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

         JPanel inputFieldPanel = new JPanel();
         carDetailsPanel.add(inputFieldPanel);
         inputFieldPanel.setLayout(new GridLayout(0, 1, 0, 0));

         JPanel vinInputPanel = new JPanel();
         inputFieldPanel.add(vinInputPanel);
         vinInputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JLabel lblBarcode = new JLabel("Vin");
         vinInputPanel.add(lblBarcode);

         JTextField barcodeField = new JTextField();
         barcodeField.setText("123456789abcdefgh");
         barcodeField.setColumns(10);
         vinInputPanel.add(barcodeField);

         JPanel addCarPanel = new JPanel();
         inputFieldPanel.add(addCarPanel);
         addCarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, PADDING));

         JButton btnConfirm = new JButton("Tilføj");
         btnConfirm.addActionListener(e -> addCar(barcodeField.getText()));
         addCarPanel.add(btnConfirm);
    }
    
    private void createOrderOverviewPanel() {
    	JPanel orderOverviewPanel = new JPanel();
        mainPanel.add(orderOverviewPanel);
        orderOverviewPanel.setLayout(new BorderLayout(0, 0));

        JPanel titlePanel = new JPanel();
        orderOverviewPanel.add(titlePanel, BorderLayout.NORTH);

        JLabel lblOrderInfo = new JLabel("Ordreoversigt");
        titlePanel.add(lblOrderInfo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        orderOverviewPanel.add(scrollPane, BorderLayout.CENTER);

        centerOfPanels = new JPanel();
        centerOfPanels.setLayout(new BoxLayout(centerOfPanels, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(centerOfPanels);
    }
    
    private void createFooter() {
        JPanel footerPanel = new JPanel();
        FlowLayout fl_footerPanel = (FlowLayout) footerPanel.getLayout();
        fl_footerPanel.setHgap(100);
        fl_footerPanel.setAlignment(FlowLayout.RIGHT);
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        JButton btnOrderCancel = new JButton("Annuller");
        btnOrderCancel.addActionListener(e -> cancel());
        footerPanel.add(btnOrderCancel);

        JButton btnOrderConfirm = new JButton("Bekræft");
        btnOrderConfirm.addActionListener(e -> confirm());
        footerPanel.add(btnOrderConfirm);
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
            } 
            else {
                throw new CarAlreadySoldException("Bil allerede solgt");
            }
        } 
        catch (DataAccessException e) {
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
        } 
        catch (SQLException | DataAccessException | EmptyOrderException e) {
        	JOptionPane.showMessageDialog(null, e, "Fejl", JOptionPane.PLAIN_MESSAGE);
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
                } 
                catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}