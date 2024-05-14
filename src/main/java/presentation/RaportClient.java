package presentation;

import connection.ConnectionFactory;
import model.ViewClient;

import javax.swing.*;
import java.sql.Connection;

public class RaportClient {
    Connection dbConnection =ConnectionFactory.getConnection();

    private JFrame frame;

    private JTable table;

    public RaportClient() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setLocationRelativeTo(null);

        frame.setTitle("Raport Clienti");


        ViewClient v = new ViewClient();

        String[][] data = v.viewClient();


        String[] columnNames = {"Nume", "Adresa"};

        table = new JTable(data, columnNames);
        table.setBounds(100, 100, 500, 500);


        JScrollPane jScrollPane = new JScrollPane(table);
        frame.add(jScrollPane);

        frame.setSize(847, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
