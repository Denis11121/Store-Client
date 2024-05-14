package model;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Clasa care furnizeaza funcționalitate pentru vizualizarea datelor despre clienti din baza de date.
 */
public class ViewClient {

    Connection dbConnection = (Connection) ConnectionFactory.getConnection();
    /**
     * Metoda pentru vizualizarea datelor despre clienți.
     *
     * @return Un tablou bidimensional de șiruri de caractere continand numele și adresele clientilor
     */
    public String[][] viewClient() {
        PreparedStatement stmt;
        try {
            stmt = dbConnection.prepareStatement("SELECT * FROM client");
            ResultSet rs = stmt.executeQuery();

            String[][] data = new String[1000][2];
            int i = 0;

            while (rs.next()) {
                Client client = new Client();
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));

                data[i][0] = String.valueOf(client.getName());
                data[i][1] = String.valueOf(client.getAddress());

                i++;
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new String[0][];

    }
}
