package model;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Clasa care furnizeaza functionalitate pentru vizualizarea datelor despre produse din baza de date.
 */
public class ViewProduct {

    Connection dbConnection = (Connection) ConnectionFactory.getConnection();
    /**
     * Metoda pentru vizualizarea datelor despre produse.
     *
     * @return Un tablou bidimensional de siruri de caractere continand numele produselor, cantitatea și pretul acestora
     */
    public String[][] viewProduct() {
        PreparedStatement stmt;
        try {
            stmt = dbConnection.prepareStatement("SELECT * FROM product");
            ResultSet rs = stmt.executeQuery();

            String[][] data = new String[1000][3];
            int i = 0;

            while (rs.next()) {
                Product product = new Product();
                product.setProductName(rs.getString("productName"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));


                data[i][0] = String.valueOf(product.getProductName());
                data[i][1] = String.valueOf(product.getQuantity());
                data[i][2] = String.valueOf(product.getPrice());

                i++;
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new String[0][];

    }
}
