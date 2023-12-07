import com.mysql.cj.jdbc.Driver;
import config.Config;
import dao.MySQLQuotesDAO;
import models.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuotesTest {

    public static void main(String[] args) {
        MySQLQuotesDAO quotesDAO = new MySQLQuotesDAO();

        // declare list of strings from quotes
//        List<String> quotesFromDb = getQuotes();
//        List<String> quotesFromDb = quotesDAO.getQuotes();

        quotesDAO.createConnection();
        List<Quote> quotesFromDb = quotesDAO.getQuotes();
//        for (String quote : quotesFromDb) {
            for (Quote quote : quotesFromDb) {
//            System.out.println(quote);
            // output for repeating table
            // System.out.println(quotesFromDb);
            System.out.println(quote.getAuthor() + " said:");
            System.out.println(quote.getContent());
        }
        // close connection
        quotesDAO.closeConnection();
    }
}
