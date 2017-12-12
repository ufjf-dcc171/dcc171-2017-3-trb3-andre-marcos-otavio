package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseLocator {

    public static DatabaseLocator instance = null;

    private DatabaseLocator() {

    }

    public static DatabaseLocator getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DatabaseLocator();
        }
        return instance;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/BDTarefas";
            Connection conexao = DriverManager.getConnection(driverURL, "root", "root");
        return conexao;
    }
}
