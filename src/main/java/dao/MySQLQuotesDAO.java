package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import models.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLQuotesDAO {
    // create methods for connection
    private Connection connection = null;

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

    public List<Quote> getQuotes() {
        List<Quote> quotes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM quotes");
            while (resultSet.next()) {
                quotes.add(new Quote(
                        resultSet.getString("author"),
                        resultSet.getString("content")
                ));
            }
        } catch (SQLException sqlx) {
            System.out.println(sqlx);


            //    public static List<String> getQuotes(){
            // removed static bc will be using in other areas
//    public List<String> getQuotes() {
//        //add connection object
////        Connection connection = null;
//
//        // declare list of strings called quotes that is an empty arraylist
//        List<String> quotes = new ArrayList<>();
//
//        //add exception
//        //use DriverManager package
//        try {
////            DriverManager.registerDriver(new Driver());
////            connection = DriverManager.getConnection(
////                    // takes three parameters to connect to db server
////                    Config.getURL(),
////                    Config.getUser(),
////                    Config.getPassword()
////            );
//
//            // create statement object
//            Statement statement = connection.createStatement();
//
//            // execute SQL statement and pass an actual SQL statement to produce a ResultSet
//            ResultSet resultSet = statement.executeQuery("SELECT content FROM quotes");
//            // use while loop and .next
//            while (resultSet.next()) {
//                quotes.add(resultSet.getString("content"));
//            }
//
//        } catch (SQLException sqlx) {
//            System.out.println(sqlx.getMessage());
////        } finally {
//            // use close connection
//            // ALWAYS close your DB connection after opening
//            if (connection != null){
//                try {
//                    connection.close();
//                } catch (SQLException sqlx){
//                    System.out.println(sqlx.getMessage());
//                }
//            }
        }


        return quotes;
    }

    // create close connection method
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

        //
        public long insertQuote(){

            // create a connection method

            // create and execute a statement

            // close connection

            return 0;

    }

}
