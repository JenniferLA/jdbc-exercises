//package lectures;
//
//import dao.MySQLDAO;
//import dao.MySQLQuotesDAO;
//import dao.MySQLUsersDAO;
//import models.User;
//
//public class PSTest {
//    // play around with insert, update, delete using prepared statements
//    public static void main(String[] args) {
//        // 1. use a DAO
//        MySQLUsersDAO userDAO = new MySQLUsersDAO();
//        userDAO.createConnection();
//
//        // adding bubba to our add users table
//        boolean userDAO.addUser("bubba", "shrimp", "bubba@gump.com");
//
//        // using long
//        long newUserID = userDAO.addUser("bubba", "shrimp", "bubba@gump.com");
//        System.out.println(newUserID);
//
//        User user = usersDAO.fetchUsersById(newUserID);
//
//
//
//        // close connection
//        userDAO.closeConnection();
//
//    }
//
//}
