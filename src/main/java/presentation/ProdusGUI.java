package presentation;

import businessLogic.ProductBLL;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdusGUI {

    private JTextField jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JButton jcomp6;
    private JButton jcomp7;
    private JButton jcomp8;
    private JButton jcomp9;
    private JLabel jcomp10;
    private JTextField jcomp11;
    private JButton jcomp12;
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProdusGUI produsGUI = new ProdusGUI();
                    produsGUI.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProdusGUI() {
        initialize();
    }

    public void initialize() {

        frame = new JFrame("ProdusGUI");
        frame.setBounds(100, 100, 300, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        //construct components
        jcomp1 = new JTextField(5);
        jcomp2 = new JLabel("PRODUS");
        jcomp3 = new JLabel("Nume Produs");
        jcomp4 = new JLabel("Cantitate");
        jcomp5 = new JTextField(5);
        jcomp6 = new JButton("Adaugare Produs");
        jcomp7 = new JButton("Stergere Produs");
        jcomp8 = new JButton("Vizualizare Produse");
        jcomp9 = new JButton("Editare Produs");
        jcomp10 = new JLabel("Pret");
        jcomp11 = new JTextField(5);
        jcomp12 = new JButton("Inapoi la Meniu");

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (jcomp1.getText() != "" && jcomp5.getText() != "" && jcomp11.getText() != "") {
                    ProductBLL productBLL = new ProductBLL();
                    Product product = new Product(jcomp1.getText(), Integer.parseInt(jcomp5.getText()), Double.parseDouble(jcomp11.getText()));
                    productBLL.insertProduct(product);
                }
            }
        });

        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (jcomp1.getText() != "" && jcomp5.getText() != "" && jcomp11.getText() != "") {
                    ProductBLL productBLL = new ProductBLL();
                    Product product = new Product(jcomp1.getText(), Integer.parseInt(jcomp5.getText()), Double.parseDouble(jcomp11.getText()));
                    productBLL.deleteProduct(product);
                }
            }
        });

        jcomp8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RaportProduct raportProduct = new RaportProduct();
            }
        });

        jcomp12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });


        frame.setPreferredSize(new Dimension(285, 372));
        frame.setLayout(null);


        frame.add(jcomp1);
        frame.add(jcomp2);
        frame.add(jcomp3);
        frame.add(jcomp4);
        frame.add(jcomp5);
        frame.add(jcomp6);
        frame.add(jcomp7);
        frame.add(jcomp8);
        frame.add(jcomp9);
        frame.add(jcomp10);
        frame.add(jcomp11);
        frame.add(jcomp12);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(105, 75, 130, 20);
        jcomp2.setBounds(115, 15, 115, 35);
        jcomp3.setBounds(15, 70, 100, 25);
        jcomp4.setBounds(15, 100, 100, 25);
        jcomp5.setBounds(105, 105, 130, 20);
        jcomp6.setBounds(60, 180, 160, 25);
        jcomp7.setBounds(60, 255, 160, 25);
        jcomp8.setBounds(60, 290, 160, 25);
        jcomp9.setBounds(60, 215, 160, 25);
        jcomp10.setBounds(15, 130, 100, 25);
        jcomp11.setBounds(105, 135, 130, 20);
        jcomp12.setBounds(60, 325, 160, 25);

    }

}
