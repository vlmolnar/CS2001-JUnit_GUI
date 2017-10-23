package uk.ac.standrews.cs.cs2001.w03;

import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeAlreadyInUseException;
import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeNotRegisteredException;
import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.common.WalletEmptyException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IFactory;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static uk.ac.standrews.cs.cs2001.w03.common.AbstractFactoryClient.getFactory;

/**
 *
 * @author <matricnumber>
 */
public class VendingMachineGUI extends JFrame {
    //START OF MY VARIABLES
    private IFactory factory;
    private IVendingMachine vendingMachine;
    private IVendingMachineProduct product1;
    private IVendingMachineProduct product2;
    private IVendingMachineProduct product3;
    private ICustomer jane;
    private ICustomer jack;
    private ICustomer currentCustomer;
    private static final String STOCK_INDICATOR = " on stock";
    private static final String TOTAL_STOCK_INDICATOR = "Total items on stock: ";
    private static final String POPULAR_INDICATOR = "Most popular item: ";
    private static final String LANE_CODE1 = "A1";
    private static final String LANE_CODE2 = "A2";
    private static final String LANE_CODE3 = "A3";
    //END OF MY VARIABLES

    //START OF GUI VARIABLES
    private JRadioButton customerJane;
    private JSpinner janeQuantitySpinner;
    private JButton janeTransferButton;
    private JLabel janeWalletLabel;
    private JSpinner jackQuantitySpinner;
    private JButton jackTransferButton;
    private JLabel jackWalletLabel;
    private ButtonGroup customerButtonGroup;
    private JRadioButton customerJack;
    private JPanel customerPanel;
    private JLabel customersLabel;
    private JLabel mostPopularLabel;
    private JPanel product1Panel;
    private JLabel product1Desc;
    private JLabel product1Quantity;
    private JPanel product2Panel;
    private JLabel product2Desc;
    private JLabel product2Quantity;
    private JPanel product3Panel;
    private JLabel product3Desc;
    private JLabel product3Quantity;
    private JButton stock1Button;
    private JSpinner stock1Spinner;
    private JButton stock2Button;
    private JSpinner stock2Spinner;
    private JButton stock3Button;
    private JSpinner stock3Spinner;
    private JLabel stockLabel;
    private JPanel stockPanel;
    private JLabel totalStock;
    private JLabel vendingMachineLabel;
    private JPanel vendingMachinePanel;
    private JLabel walletLabel;
    private JLabel transferMoneyLabel;
    private JSpinner product1BuySpinner;
    private JSpinner product2BuySpinner;
    private JSpinner product3BuySpinner;
    private JButton product1BuyButton;
    private JButton product2BuyButton;
    private JButton product3BuyButton;
    //END OF GUI VARIABLES

    /**
     * Creates new form VendingMachineGUI
     */
    public VendingMachineGUI() {
        initComponents();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("GUI ERROR!");
            System.err.println(ex.getMessage());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendingMachineGUI().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        //INITIALISING MY VARIABLES
        factory = getFactory();
        vendingMachine = factory.makeVendingMachine();
        product1 = factory.makeVendingMachineProduct(LANE_CODE1, "Haggis Crisps", 2.48);
        product2 = factory.makeVendingMachineProduct(LANE_CODE2, "Canned Coffee", 1.99);
        product3 = factory.makeVendingMachineProduct(LANE_CODE3, "Chocolate Bar", 3.52);
        jane = factory.makeCustomer();
        jack = factory.makeCustomer();
        currentCustomer = jane;
        try {
            vendingMachine.registerProduct(product1);
            vendingMachine.registerProduct(product2);
            vendingMachine.registerProduct(product3);

        } catch (LaneCodeAlreadyInUseException e) {
            System.err.println("ERROR: lane code already in use!");
        }

        //INITIALISING GUI VARIABLES
        customerButtonGroup = new ButtonGroup();
        customerPanel = new JPanel();
        customersLabel = new JLabel();
        customerJane = new JRadioButton();
        customerJack = new JRadioButton();
        walletLabel = new JLabel();
        transferMoneyLabel = new JLabel();
        janeTransferButton = new JButton();
        jackTransferButton = new JButton();
        janeWalletLabel = new JLabel();
        jackWalletLabel = new JLabel();
        janeQuantitySpinner = new JSpinner();
        jackQuantitySpinner = new JSpinner();
        vendingMachinePanel = new JPanel();
        vendingMachineLabel = new JLabel();
        product1Panel = new JPanel();
        product1Desc = new JLabel();
        product1BuySpinner = new JSpinner();
        product1BuyButton = new JButton();
        product2Panel = new JPanel();
        product2Desc = new JLabel();
        product2BuySpinner = new JSpinner();
        product2BuyButton = new JButton();
        product3Panel = new JPanel();
        product3Desc = new JLabel();
        product3BuySpinner = new JSpinner();
        product3BuyButton = new JButton();
        stockPanel = new JPanel();
        stockLabel = new JLabel();
        product1Quantity = new JLabel();
        product2Quantity = new JLabel();
        product3Quantity = new JLabel();
        stock1Spinner = new JSpinner();
        stock2Spinner = new JSpinner();
        stock3Spinner = new JSpinner();
        stock1Button = new JButton();
        stock2Button = new JButton();
        stock3Button = new JButton();
        mostPopularLabel = new JLabel();
        totalStock = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        customerPanel.setBackground(new java.awt.Color(144, 144, 144));

        customersLabel.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        customersLabel.setText("Customers");

        customerButtonGroup.add(customerJane);
        customerJane.setSelected(true);
        customerJane.setText("Jane");
        customerJane.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                customerJaneItemStateChanged(evt);
            }
        });

        customerButtonGroup.add(customerJack);
        customerJack.setText("Jack");

        walletLabel.setText("Wallet");

        transferMoneyLabel.setText("Transfer money");

        janeTransferButton.setText("Transfer");
        janeTransferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                janeTransferActionPerformed(evt);
            }
        });

        jackTransferButton.setText("Transfer");
        jackTransferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jackTransferActionPerformed(evt);
            }
        });
        jackTransferButton.setEnabled(false);

        janeWalletLabel.setText("£0");

        jackWalletLabel.setText("£0");

        janeQuantitySpinner.setModel(new SpinnerNumberModel(0.00d, 0.00d, null, 1d));

        jackQuantitySpinner.setModel(new SpinnerNumberModel(0.00d, 0.00d, null, 0.1d));
        jackQuantitySpinner.setEnabled(false);

        GroupLayout CustomerPanelLayout = new GroupLayout(customerPanel);
        customerPanel.setLayout(CustomerPanelLayout);
        CustomerPanelLayout.setHorizontalGroup(
            CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(customersLabel)
                        .addGap(161, 161, 161)
                        .addComponent(walletLabel))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(customerJane)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(janeWalletLabel))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(customerJack)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jackWalletLabel)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(transferMoneyLabel)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(janeQuantitySpinner, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jackQuantitySpinner, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jackTransferButton)
                            .addComponent(janeTransferButton, GroupLayout.Alignment.TRAILING))))
                .addGap(42, 42, 42))
        );
        CustomerPanelLayout.setVerticalGroup(
            CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customersLabel)
                    .addComponent(walletLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferMoneyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customerJane)
                    .addComponent(janeTransferButton)
                    .addComponent(janeWalletLabel)
                    .addComponent(janeQuantitySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customerJack)
                    .addComponent(jackTransferButton)
                    .addComponent(jackWalletLabel)
                    .addComponent(jackQuantitySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        vendingMachinePanel.setBackground(new java.awt.Color(144, 144, 144));

        vendingMachineLabel.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        vendingMachineLabel.setText("Vending machine");

        product1Desc.setText(product1.getDescription());

        //START OF MY CODE
        product1BuySpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        product2BuySpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        product3BuySpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        stock1Spinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        stock2Spinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        stock3Spinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        //END OF MY CODE

        product1BuyButton.setText("Buy");
        product1BuyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                product1BuyActionPerformed(evt);
            }
        });

        GroupLayout Product1Layout = new GroupLayout(product1Panel);
        product1Panel.setLayout(Product1Layout);
        Product1Layout.setHorizontalGroup(
            Product1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(Product1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(Product1Layout.createSequentialGroup()
                        .addComponent(product1BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product1BuyButton))
                    .addComponent(product1Desc))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        Product1Layout.setVerticalGroup(
            Product1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(product1Desc)
                .addGap(40, 40, 40)
                .addGroup(Product1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(product1BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(product1BuyButton))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        product2Desc.setText(product2.getDescription());

        product2BuyButton.setText("Buy");
        product2BuyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                product2Buy2ActionPerformed(evt);
            }
        });

        GroupLayout Product2Layout = new GroupLayout(product2Panel);
        product2Panel.setLayout(Product2Layout);
        Product2Layout.setHorizontalGroup(
            Product2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product2Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(Product2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(product2Desc)
                    .addGroup(Product2Layout.createSequentialGroup()
                        .addComponent(product2BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product2BuyButton)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Product2Layout.setVerticalGroup(
            Product2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(product2Desc)
                .addGap(38, 38, 38)
                .addGroup(Product2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(product2BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(product2BuyButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        product3Desc.setText(product3.getDescription());

        product3BuyButton.setText("Buy");
        product3BuyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                product3BuyActionPerformed(evt);
            }
        });

        GroupLayout Product3Layout = new GroupLayout(product3Panel);
        product3Panel.setLayout(Product3Layout);
        Product3Layout.setHorizontalGroup(
            Product3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(Product3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(product3Desc)
                    .addGroup(Product3Layout.createSequentialGroup()
                        .addComponent(product3BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product3BuyButton)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        Product3Layout.setVerticalGroup(
            Product3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Product3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(product3Desc)
                .addGap(36, 36, 36)
                .addGroup(Product3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(product3BuySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(product3BuyButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout VendingMachinePanelLayout = new GroupLayout(vendingMachinePanel);
        vendingMachinePanel.setLayout(VendingMachinePanelLayout);
        VendingMachinePanelLayout.setHorizontalGroup(
            VendingMachinePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(VendingMachinePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VendingMachinePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(VendingMachinePanelLayout.createSequentialGroup()
                        .addComponent(vendingMachineLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(VendingMachinePanelLayout.createSequentialGroup()
                        .addComponent(product1Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(product2Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(product3Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        VendingMachinePanelLayout.setVerticalGroup(
            VendingMachinePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(VendingMachinePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vendingMachineLabel)
                .addGap(18, 18, 18)
                .addGroup(VendingMachinePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(product2Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product1Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product3Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        stockPanel.setBackground(new java.awt.Color(144, 144, 144));

        stockLabel.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        stockLabel.setText("Stock");

        product1Quantity.setText(0 + STOCK_INDICATOR);

        product2Quantity.setText(0 + STOCK_INDICATOR);

        product3Quantity.setText(0 + STOCK_INDICATOR);

        stock1Button.setText("Add Stock");
        stock1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stock1ButtonActionPerformed(evt);
            }
        });

        stock2Button.setText("Add Stock");
        stock2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stock2ButtonActionPerformed(evt);
            }
        });

        stock3Button.setText("Add Stock");
        stock3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stock3ButtonActionPerformed(evt);
            }
        });

        mostPopularLabel.setText(POPULAR_INDICATOR + "none");

        totalStock.setText(TOTAL_STOCK_INDICATOR + 0);

        GroupLayout StockPanelLayout = new GroupLayout(stockPanel);
        stockPanel.setLayout(StockPanelLayout);
        StockPanelLayout.setHorizontalGroup(
            StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(StockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(StockPanelLayout.createSequentialGroup()
                        .addComponent(stockLabel)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, StockPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(StockPanelLayout.createSequentialGroup()
                                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(StockPanelLayout.createSequentialGroup()
                                        .addComponent(stock1Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stock1Button)
                                        .addGap(90, 90, 90)
                                        .addComponent(stock2Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stock2Button))
                                    .addGroup(StockPanelLayout.createSequentialGroup()
                                        .addComponent(product1Quantity)
                                        .addGap(153, 153, 153)
                                        .addComponent(product2Quantity)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(GroupLayout.Alignment.TRAILING, StockPanelLayout.createSequentialGroup()
                                        .addComponent(stock3Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stock3Button)
                                        .addGap(12, 12, 12))
                                    .addGroup(GroupLayout.Alignment.TRAILING, StockPanelLayout.createSequentialGroup()
                                        .addComponent(product3Quantity)
                                        .addGap(74, 74, 74)))
                                .addGap(48, 48, 48))
                            .addGroup(StockPanelLayout.createSequentialGroup()
                                .addComponent(mostPopularLabel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalStock, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))))))
        );
        StockPanelLayout.setVerticalGroup(
            StockPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(StockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stockLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(product1Quantity)
                    .addComponent(product2Quantity)
                    .addComponent(product3Quantity))
                .addGap(18, 18, 18)
                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(stock1Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stock2Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stock3Spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stock1Button)
                    .addComponent(stock2Button)
                    .addComponent(stock3Button))
                .addGap(33, 33, 33)
                .addGroup(StockPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mostPopularLabel)
                    .addComponent(totalStock))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(customerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendingMachinePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vendingMachinePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void customerJaneItemStateChanged(ItemEvent evt) {
        if (customerJane.isSelected()) {
            currentCustomer = jane;
            jackQuantitySpinner.setEnabled(false);
            jackTransferButton.setEnabled(false);
            janeQuantitySpinner.setEnabled(true);
            janeTransferButton.setEnabled(true);
        } else {
            currentCustomer = jack;
            jackQuantitySpinner.setEnabled(true);
            jackTransferButton.setEnabled(true);
            janeQuantitySpinner.setEnabled(false);
            janeTransferButton.setEnabled(false);
        }
    }

    //jane's transfer button
    private void janeTransferActionPerformed(ActionEvent evt) {
        customerTransfer(jane, janeQuantitySpinner, janeWalletLabel);
    }

    //jack's transfer button
    private void jackTransferActionPerformed(ActionEvent evt) {
        customerTransfer(jack, jackQuantitySpinner, jackWalletLabel);
    }

    private void customerTransfer(ICustomer customer, JSpinner jSpinner, JLabel walletText) {
        double d = (double) jSpinner.getValue();
        try {
            customer.addMoney(d);
            jSpinner.setValue(0.00d);
            walletText.setText("£" + customer.getWallet());
        } catch (WalletEmptyException e) {
            System.err.println("ERROR: invalid money input");
        }
    }

    private void product1BuyActionPerformed(ActionEvent evt) {
        buySpinner(product1BuySpinner, LANE_CODE1, product1Quantity);
    }

    private void product2Buy2ActionPerformed(ActionEvent evt) {
        buySpinner(product2BuySpinner, LANE_CODE2, product2Quantity);
    }

    private void product3BuyActionPerformed(ActionEvent evt) {
        buySpinner(product3BuySpinner, LANE_CODE3, product3Quantity);
    }

    private void buySpinner(JSpinner jSpinner, String laneCode, JLabel labelQuantity) {
        try {
            int spinnerCurrent = (int) jSpinner.getValue();
            int numberOfItems = vendingMachine.getNumberOfItems(laneCode);
            if (spinnerCurrent <= numberOfItems) {
                vendingMachine.buyItem(laneCode, spinnerCurrent, currentCustomer);
                jSpinner.setValue(0);
                labelQuantity.setText(vendingMachine.getNumberOfItems(laneCode) + STOCK_INDICATOR);
                mostPopularLabel.setText(POPULAR_INDICATOR + mostPopularName());
                totalStock.setText(TOTAL_STOCK_INDICATOR + vendingMachine.getTotalNumberOfItems());
                if (currentCustomer == jane) {
                    janeWalletLabel.setText("£" + jane.getWallet());
                } else {
                    jackWalletLabel.setText("£"+ jack.getWallet());
                }
            }
        } catch (ProductUnavailableException | LaneCodeNotRegisteredException | WalletEmptyException e) {
            System.err.println("ERROR: Ran into Exception while buying product");
        }
    }

    private void stock1ButtonActionPerformed(ActionEvent evt) {
        stockButtonAction(LANE_CODE1, stock1Spinner, product1Quantity);
    }

    private void stock2ButtonActionPerformed(ActionEvent evt) {
        stockButtonAction(LANE_CODE2, stock2Spinner, product2Quantity);
    }

    private void stock3ButtonActionPerformed(ActionEvent evt) {
        stockButtonAction(LANE_CODE3, stock3Spinner, product3Quantity);
    }

    private void stockButtonAction(String laneCode, JSpinner spinner, JLabel quantityLabel) {
        try {
            vendingMachine.addItem(laneCode,(int) spinner.getValue());
            quantityLabel.setText(vendingMachine.getNumberOfItems(laneCode) + STOCK_INDICATOR);
            spinner.setValue(0);
            totalStock.setText(TOTAL_STOCK_INDICATOR + vendingMachine.getTotalNumberOfItems());
        } catch (LaneCodeNotRegisteredException e) {
            System.err.println("ERROR: lane code not registered!");
        }
    }

    private String mostPopularName() {

        try {
            IVendingMachineProduct popularProduct = vendingMachine.getMostPopular();
            if (popularProduct != null) {
                return popularProduct.getDescription();
            } else {
                return "none";
            }

        }catch (LaneCodeNotRegisteredException e) {
            System.err.println("ERROR: no lane code registered!");
            return "oops";
        }
    }
}
