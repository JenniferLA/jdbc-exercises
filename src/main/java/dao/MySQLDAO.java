package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAO {
    protected Connection connection = null;

    public void createConnection() {
        // create structure
        System.out.println("Connecting to db...");
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    // takes three parameters to connect to db server
                    Config.getURL(),
                    Config.getUser(),
                    Config.getPassword()
            );
        } catch (SQLException sqlx) {
            System.out.println("Error connecting to db..." + sqlx.getMessage());
        }
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlx) {
                System.out.println(sqlx.getMessage());
            }
            System.out.println("Closing DB connection...");
        }
    }
}
