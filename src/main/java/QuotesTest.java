import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuotesTest {

    public static List<String> getQuotes(){
        //add connection object
        Connection connection = null;

        // declare list of strings called quotes that is an empty arraylist
        List<String> quotes = new ArrayList<>();

        //add exception
        //use DriverManager package
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    // takes three parameters to connect to db server
                    Config.getURL(),
                    Config.getUser(),
                    Config.getPassword()
            );

            // create statement object
            Statement statement = connection.createStatement();

            // execute SQL statement and pass an actual SQL statement to produce a ResultSet
            ResultSet resultSet = statement.executeQuery("SELECT content FROM quotes");
            // use while loop and .next
            while(resultSet.next()){
                quotes.add(resultSet.getString("content"));
            }

        } catch (SQLException sqlx){


            System.out.println(sqlx.getMessage());
        } finally {
            // use close connection
            // ALWAYS close your DB connection after opening
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException sqlx){
                    System.out.println(sqlx.getMessage());
                }
            }
        }


        return quotes;
    }

    public static void main(String[] args) {
    // declare list of strings from quotes
        List<String> quotesFromDb = getQuotes();
        for (String quote : quotesFromDb) {
            System.out.println(quote);

        }
    }
}
