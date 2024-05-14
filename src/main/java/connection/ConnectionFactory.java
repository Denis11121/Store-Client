package connection;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "sdv29032003";
    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
     * Constructorul clasei singleton ConnectionFactory
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda care realizeaza conexiunea cu baza de date
     *
     * @return Conexiunea spre baza de date
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            ex.printStackTrace();
        }
        return connection;
    }
    /**
     * Metoda care va permite preluarea conexiunii spre baza de date
     *
     * @return Conexiunea spre baza de date
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }
    /**
     * Metoda care permite inchiderea conexiunii
     *
     * @param connection Conexiunea care se va inchide
     */
    public static void close(java.sql.Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
                ex.printStackTrace();
            }
        }
    }
    /**
     * Metoda care va permite inchiderea unei interogari
     *
     * @param statement Interogarea care se va inchide
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     * Metoda care va permite inchiderea unui set de rezultate
     *
     * @param resultSet Setul de rezultate care se va inchide
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
