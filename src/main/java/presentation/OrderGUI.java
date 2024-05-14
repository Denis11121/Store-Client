package presentation;

import businessLogic.OrderBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderGUI {

    private JTextField txtNumeClient;
    private JTextField txtAdresaClient;
    private JButton btnComanda;
    private JButton btnInapoi;
    private JLabel lblNumeClient;
    private JLabel lblAdresaClient;
    private JLabel lblNumeProdus;
    private JLabel lblCantitate;
    private JLabel lblPret;
    private JTextField txtNumeProdus;
    private JTextField txtPretProdus;
    private JTextField txtCantitate;
    private JLabel lblTitlu;
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OrderGUI orderGUI = new OrderGUI();
                orderGUI.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public OrderGUI() {
        initialize();
    }

    public void initialize() {

        frame = new JFrame("Comandă");
        frame.setBounds(100, 100, 600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Components initialization
        lblTitlu = new JLabel("COMANDĂ");
        lblTitlu.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitlu.setBounds(260, 20, 100, 25);

        lblNumeClient = new JLabel("Nume Client:");
        lblNumeClient.setBounds(25, 95, 100, 25);

        lblAdresaClient = new JLabel("Adresă:");
        lblAdresaClient.setBounds(25, 135, 100, 25);

        txtNumeClient = new JTextField();
        txtNumeClient.setBounds(110, 95, 120, 25);

        txtAdresaClient = new JTextField();
        txtAdresaClient.setBounds(110, 135, 120, 25);

        lblNumeProdus = new JLabel("Nume Produs:");
        lblNumeProdus.setBounds(275, 90, 100, 25);

        lblPret = new JLabel("Pret:");
        lblPret.setBounds(315, 130, 100, 25);

        lblCantitate = new JLabel("Cantitate:");
        lblCantitate.setBounds(520, 85, 100, 25);

        txtNumeProdus = new JTextField();
        txtNumeProdus.setBounds(380, 95, 100, 25);

        txtPretProdus = new JTextField();
        txtPretProdus.setBounds(380, 135, 100, 25);

        txtCantitate = new JTextField();
        txtCantitate.setBounds(525, 125, 45, 35);

        btnComanda = new JButton("Comandă");
        btnComanda.setBounds(210, 200, 160, 25);

        btnInapoi = new JButton("Inapoi la meniu");
        btnInapoi.setBounds(210, 245, 160, 25);

        btnComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNumeClient.getText().isEmpty() && !txtAdresaClient.getText().isEmpty() && !txtNumeProdus.getText().isEmpty() && !txtPretProdus.getText().isEmpty() && !txtCantitate.getText().isEmpty()) {
                    OrderBLL orderBLL = new OrderBLL();
                    Client client = new Client(txtNumeClient.getText(), txtAdresaClient.getText());
                    Product product = new Product(txtNumeProdus.getText(), Integer.parseInt(txtCantitate.getText()), Double.parseDouble(txtPretProdus.getText()));
                    orderBLL.insertOrder(client, product, Integer.parseInt(txtCantitate.getText()));
                }
            }
        });

        btnInapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(lblTitlu);
        frame.getContentPane().add(lblNumeClient);
        frame.getContentPane().add(lblAdresaClient);
        frame.getContentPane().add(txtNumeClient);
        frame.getContentPane().add(txtAdresaClient);
        frame.getContentPane().add(lblNumeProdus);
        frame.getContentPane().add(lblPret);
        frame.getContentPane().add(lblCantitate);
        frame.getContentPane().add(txtNumeProdus);
        frame.getContentPane().add(txtPretProdus);
        frame.getContentPane().add(txtCantitate);
        frame.getContentPane().add(btnComanda);
        frame.getContentPane().add(btnInapoi);
    }
}
