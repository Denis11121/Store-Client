package presentation;

import connection.ConnectionFactory;
import model.ViewProduct;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class RaportProduct {
    Connection dbConnection = ConnectionFactory.getConnection();
    private JFrame frame;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RaportProduct window = new RaportProduct();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public RaportProduct() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setLocationRelativeTo(null);

        frame.setTitle("Raport Produs");


        ViewProduct v = new ViewProduct();
        String[][] data = v.viewProduct();

        String[] columnNames = {"Nume", "Cantitate", "Pret"};

        table = new JTable(data, columnNames);
        table.setBounds(100, 100, 500, 500);


        JScrollPane jScrollPane = new JScrollPane(table);
        frame.add(jScrollPane);

        frame.setSize(847, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
