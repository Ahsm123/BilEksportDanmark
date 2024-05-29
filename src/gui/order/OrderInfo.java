package gui.order;

import controller.OrderCtrl;
import gui.Main;
import gui.TextInput;
import gui.exceptions.InvalidVinException;
import gui.supers.GUIPanel;
import model.Copy;
import model.Order;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CopyNotReady;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderInfo extends GUIPanel {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
    private JPanel centerOfPanels;
    
    private LinkedList<CopyPanel> carPanels;
    private CheckIfSoldThread thread;
    private volatile boolean threadNeedsToRun = true;
    
    private OrderCtrl orderCtrl;

    public OrderInfo(OrderCtrl orderCtrl) {
    	super(450, 300);
    	init(orderCtrl);
        createMainPanel();
        createDetailsPanel();
        createFooter();
        createOrderOverviewPanel();
    }
    
    private void init(OrderCtrl orderCtrl) {
    	maingui = Main.getInstance();
        this.orderCtrl = orderCtrl;
        this.carPanels = new LinkedList<>();
        thread = new CheckIfSoldThread(orderCtrl, this);
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
         barcodeField.setText("KNDJP3A55E7006855");
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
        TextInput textInput = new TextInput();

        try {
            boolean isValidVin = textInput.VINValidator(input);

            if (isValidVin) {
                createCarPanel(input);
            }
        } catch (InvalidVinException e) {
            showErrorPopup(e.getMessage());
        } catch (CarAlreadySoldException e) {
            showErrorPopup("Bil allerede solgt");
        } catch (SQLException e) {
            showErrorPopup("Database fejl");
        } catch (CopyNotReady e) {
            showErrorPopup("Bilen mangler dokumenter og kan derfor ikke sælges");
        }
    }

    private void createCarPanel(String input) throws SQLException, CarAlreadySoldException, CopyNotReady {
        try {
            if (!orderCtrl.isCopyInAnOrder(input)) {
                Copy copy = orderCtrl.addCopy(input);

                if (!thread.isAlive()) {
                    thread.start();
                }
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
            showErrorPopup("Database fejl");
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
        } 
        catch (EmptyOrderException e) {
            showErrorPopup("Ingen bil i ordren");
        }
    }
    
    @Override
	protected void cancel() {
        threadNeedsToRun = false;
        maingui.goBack();
    }

    private void confirm() {
        try {
            threadNeedsToRun = false;
            Order order = orderCtrl.confirmOrder();
            maingui.switchFrameTo(new OrderPrint(order), false);
        } 
        catch (SQLException | DataAccessException e) {
        	showErrorPopup("Database fejl");
        }
        catch (EmptyOrderException e) {
        	showErrorPopup("Ingen bil i ordren");
        }
    }
    
    public LinkedList<CopyPanel> getPanels() {
        return carPanels;
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
                        showErrorPopup("En eller flere biler på ordren er allerede solgt og er derfor blevet fjernet");
                    }
                    sleep(SLEEP_TIME);
                } 
                catch (InterruptedException | SQLException e) {
                    showErrorPopup("Kunne ikke automatisk fjerne solgte biler");
                }
            }
        }
    }
}