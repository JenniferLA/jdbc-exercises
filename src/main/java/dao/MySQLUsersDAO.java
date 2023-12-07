//package dao;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class MySQLUsersDAO extends MySQLDAO {
//
//    public boolean checkCredentials(String login, String pw) {
//        try {
//            Statement st = connection.createStatement();
//            // get the query perfect before plugging into to Java
//            ResultSet rs = st.executeQuery("select count(*) as user_count" +
//                    "from users " +
//                    "where name = '" + login + "' " +
//                    "and password = '" + pw + "'");
//            rs.next();
//            int userCount = rs.getInt("user_count");
//            if(userCount > 0) {
//                return true;
//            }
//            return false;
//        } catch (SQLException e) {
//            // wrapping checked exceptions in an unchecked exception
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    // Parameterized Query to avoid MySQL injection Queries
//    public boolean checkCredentialsSafe(String login, String pw) {
//        try {
//            PreparedStatement st = connection.prepareStatement("select count(*) as user_count " +
//                    "from users " +
//                    "where username = ? " +
//                    "and password = ? ");
//            // must have a parameter index starting at one
//            // must have a set function for each question mark placeholder
//            st.setString(1, login);
//            st.setString(2, pw);
//            ResultSet rs = st.executeQuery();
//            rs.next();
//            int userCount = rs.getInt("user_count");
//            if (userCount > 0) {
//                return true;
//            }
//            return false;
//        } catch (SQLException e) {
//            // wrapping checked exceptions in an unchecked exception
//            throw new RuntimeException(e);
//        }
//
//
//        public boolean addUser (String username, String pw, String email){
//            PreparedStatement st = null;
//            try {
//                st = connection.prepareStatement("insert into blog_users" +
//                        "(email, password, username)" +
//                        "values(?, ?, ?)");
//                st.setString(1, email);
//                st.setString(2, pw);
//                st.setString(3, username);
//
//                // executeUpdate returns int
//                int numInserted = st.executeUpdate();
//
//                return numInserted == 1;
//            } catch (SQLException e) {
//                throw new RuntimeException(e){
//                    e.
//                }
//            }
//
//        }
//        // returns  the new user's id if successful or throws exception if anything bad happens
//        public long addUser (String username, String pw, String email){
//            PreparedStatement st = null;
//            try {
//                st = connection.prepareStatement("insert into blog_users" +
//                        "(email, password, username)" +
//                        "values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//                st.setString(1, email);
//                st.setString(2, pw);
//                st.setString(3, username);
//
//                // executeUpdate returns int
//                int numInserted = st.executeUpdate();
//
//                ResultSet keys = st.getGeneratedKeys();
//                keys.next();
//
//                long newID = keys.getLong(1);
//                return newID;
//
//                return numInserted == 1;
//            } catch (SQLException e) {
//                throw new RuntimeException(e){
////                    e.
//                }
//            }
//
//        }
//    }
//}
