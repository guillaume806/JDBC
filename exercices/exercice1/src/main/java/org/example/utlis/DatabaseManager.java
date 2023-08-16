package org.example.utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public static Connection getPostgreSQLConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/execiceJDBC";
        String user = "postgres";
        String password = "Superadmin";

        Connection connection = DriverManager.getConnection(url,user,password);

        return connection;
    }
}
