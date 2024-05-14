package presentation;

import businessLogic.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JPanel {

    private JTextField jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JButton jcomp6;
    private JButton jcomp7;
    private JButton jcomp8;
    private JButton jcomp9;
    private JButton jcomp10;
    private JFrame frame = new JFrame("ClientGUI");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientGUI clientGUI = new ClientGUI();
                    clientGUI.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClientGUI() {
        initialize();
    }

    public void initialize() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        jcomp1 = new JTextField(10);
        jcomp2 = new JLabel("CLIENT");
        jcomp2.setFont(new Font("Arial", Font.BOLD, 20));
        jcomp3 = new JLabel("Nume");
        jcomp4 = new JLabel("Adresa");
        jcomp5 = new JTextField(10);
        jcomp6 = new JButton("Adaugare Client");
        jcomp7 = new JButton("Stergere Client");
        jcomp8 = new JButton("Vizualizare Clienti");
        jcomp9 = new JButton("Editare Client");
        jcomp10 = new JButton("Inapoi la Meniu");

        contentPanel.setBackground(Color.WHITE);
        jcomp2.setForeground(Color.BLACK);
        jcomp6.setBackground(Color.GRAY);
        jcomp7.setBackground(Color.GRAY);
        jcomp8.setBackground(Color.GRAY);
        jcomp9.setBackground(Color.GRAY);
        jcomp10.setBackground(Color.GRAY);

        contentPanel.add(jcomp2);
        contentPanel.add(new JLabel(""));
        contentPanel.add(jcomp3);
        contentPanel.add(jcomp1);
        contentPanel.add(jcomp4);
        contentPanel.add(jcomp5);
        contentPanel.add(jcomp6);
        contentPanel.add(jcomp7);
        contentPanel.add(jcomp8);
        contentPanel.add(jcomp9);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(jcomp10, BorderLayout.SOUTH);

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!jcomp1.getText().isEmpty() && !jcomp5.getText().isEmpty()) {
                    ClientBLL clientBLL = new ClientBLL();
                    Client client = new Client(jcomp1.getText(), jcomp5.getText());
                    clientBLL.insertClient(client);
                }
            }
        });

        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!jcomp1.getText().isEmpty() && !jcomp5.getText().isEmpty()) {
                    ClientBLL clientBLL = new ClientBLL();
                    Client client = new Client(jcomp1.getText(), jcomp5.getText());
                    clientBLL.deleteClient(client);
                }
            }
        });

        jcomp10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        jcomp8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RaportClient raportClient = new RaportClient();
            }
        });
    }
}