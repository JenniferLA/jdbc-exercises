package contacts_manager.dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import contacts_manager.models.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLContactsDAO implements ContactsDAO {
    protected Connection connection = null;

    public MySQLContactsDAO() {
        open();
    }

    public void open() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.getURL(),
                    Config.getUser(),
                    Config.getPassword()
            );
            System.out.println("Connection created.");
        } catch (SQLException sqlx) {
            System.out.println("Connection failed!!!");
        }
    }

    public void close() {
        if (connection == null) {
            System.out.println("Connection aborted.");
            return;
        }
        try {
            connection.close();

            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }


    public static void outputList(List<Contact> contacts) {
        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + " | " + contact.getNumber());
        }
    }

    @Override
    public List<Contact> fetchContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contacts");
            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getString("name"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException sqlx) {
            System.out.println(sqlx);
        }
        return contacts;
    }

    @Override
    public long insertContact(Contact contact) {
        long id = 0;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("INSERT INTO contacts " +
                            " (name, phone)" +
                            " values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, contact.getName());
            st.setString(2, contact.getNumber());

            int numInserted = st.executeUpdate();

            ResultSet keys = st.getGeneratedKeys();
            keys.next();

            id = keys.getLong(1);
            return id;
        } catch (SQLException sqlx) {
            throw new RuntimeException(sqlx);
        }
    }

    @Override
    public void deleteByName(String name) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM contacts " +
                    " WHERE name = ? ");
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException sqlx) {
            throw new RuntimeException(sqlx.getMessage());
        }
    }

    @Override
    public List<Contact> searchContacts(String searchTerm) {
        List<Contact> contacts = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM contacts" +
                    " WHERE name = ? ");
            st.setString(1, searchTerm);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setName(rs.getString("name"));
                contact.setNumber(rs.getString("phone"));
//                contacts.add(contact);
                System.out.println(contact.getName() + " | " + contact.getNumber());
            }

        } catch (SQLException sqlx) {
            throw new RuntimeException(sqlx + "There is an error!");
        }
        return contacts;
    }
}
