package dataAccess;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogDAO {
    private static final Logger LOGGER = Logger.getLogger(LogDAO.class.getName());
    /**
     * Insereaza o factura în tabelul de log
     *
     * @param bill Factura de inserat in log
     */
    public void insertBill(Bill bill) {
        String insertQuery = "INSERT INTO Log (orderId, clientName, productName, price, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setDouble(4, bill.price());
            statement.setInt(5, bill.quantity());

            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, "Error inserting bill into Log table: " + ex.getMessage(), ex);
        }
    }
}
