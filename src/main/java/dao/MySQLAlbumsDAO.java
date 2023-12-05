package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import models.Album;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAlbumsDAO {
    // initialize the connection to null so we know whether or not to close it when done
    private Connection connection = null;

    public void createConnection() throws MySQLAlbumsException {
        System.out.print("Trying to connect... ");
        try {
            //TODO: create the connection and assign it to the instance variable
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.getURL(),
                    Config.getUser(),
                    Config.getPassword()
            );
            System.out.println("connection created.");
        } catch (SQLException e) {
            throw new MySQLAlbumsException("connection failed!!!");
        }
    }

    int getTotalAlbums() throws MySQLAlbumsException {
        int count = 0;
        try {
            //TODO: fetch the total number of albums from the albums table and assign it to the local variable
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM albums");
            while (resultSet.next()) {
                count ++;

            // Option 1  loop over ResultSet and increment count
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(
//                        "SELECT * FROM ");
//                while (resultSet.next()){
//                 count ++;
//                }

            // Option 2: Get the COUNT(*) and display it
//            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM albums");
//            resultSet.next();
//            // resultSet get... methods accept either a column name or a column index
//                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");
        }
        return count;
    }

    // Create helper method that takes ResultSet as a parameter and makes/returns an Album from it
//    private Album makeAlbumFromDB(ResultSet record) throws MySQLAlbumsException {
//        try {
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM albums");
//            while (rs.next()) {
//                albums.add(new Album(
//                        rs.getLong("id"),
//                        rs.getString("artist"),
//                        rs.getString("name"),
//                        rs.getInt("release_date"),
//                        rs.getDouble("sales"),
//                        rs.getString("genre")
//                ));
//            }
//        } catch (SQLException sqlx) {
//            System.out.println(sqlx);
//        }
//
//        return albums;
//    }

    public List<Album> fetchAlbums() {
        List<Album> albums = new ArrayList<>();

        // TODO: write your code here
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM albums");
            while (rs.next()) {
                albums.add(new Album(
                        rs.getLong("id"),
                        rs.getString("artist"),
                        rs.getString("name"),
                        rs.getInt("release_date"),
                        rs.getDouble("sales"),
                        rs.getString("genre")
                ));
            }
        } catch (SQLException sqlx) {
            System.out.println(sqlx);
        }

        return albums;
    }

    public Album fetchAlbumById(long id) {
        Album album = null;

        // TODO: write your code here
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM albums " +
                    " WHERE id = ? ");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            album = new Album();
            album.setId(rs.getLong("id"));
            album.setArtist(rs.getString("artist"));
            album.setName(rs.getString("name"));
            album.setReleaseDate(rs.getInt("release_date"));
            album.setSales(rs.getDouble("sales"));
            album.setGenre(rs.getString("genre"));
        } catch (SQLException sqlx) {
            throw new RuntimeException(sqlx);
        }

        return album;
    }

    // Note that insertAlbum should return the id that MySQL creates for the new inserted album record
    public long insertAlbum(Album album) throws MySQLAlbumsException {
        long id = 0;

        // TODO: write your code here
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("insert into albums " +
                    " (artist, name, release_date, sales, genre) " +
                    " values (?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, album.getArtist());
            st.setString(2, album.getName());
            st.setString(3, String.valueOf(album.getReleaseDate()));
            st.setString(4, String.valueOf(album.getSales()));
            st.setString(5, album.getGenre());

            int numInserted = st.executeUpdate();

            ResultSet keys = st.getGeneratedKeys();
            keys.next();

            id = keys.getLong(1);
            return id;

        } catch (SQLException sqlx) {
            throw new RuntimeException(sqlx);
        }

    }

    public void updateAlbum(Album album) throws MySQLAlbumsException {

        // TODO: write your code here
        try {
            PreparedStatement st = connection.prepareStatement("update albums " +
                    " set name = ? " +
                    " where id = ? ");
            st.setString(1, album.getName());
            st.setLong(2, album.getId());
            st.executeUpdate();
        } catch (SQLException sqlx){
            throw new RuntimeException(sqlx);
        }

    }

    public void deleteAlbumById(long id) throws MySQLAlbumsException {

        // TODO: write your code here
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("delete from albums " +
                    " where id = ? ");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException sqlx) {
            throw new RuntimeException();
        }

    }




    public void closeConnection() {
        if(connection == null) {
            System.out.println("Connection aborted.");
            return;
        }
        try {
            //TODO: close the connection
            connection.close();

            System.out.println("Connection closed.");
        } catch(SQLException e) {
            // ignore this
        }
    }

}

