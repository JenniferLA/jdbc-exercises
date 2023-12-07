//package lectures;
//
//import dao.MySQLDAO;
//import dao.MySQLQuotesDAO;
//import dao.MySQLUsersDAO;
//
//import java.util.Scanner;
//
//public class UserInputTest {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
//        // move it to the top to check this first
//        MySQLUsersDAO userDAO = new MySQLUsersDAO();
//        userDAO.createConnection();
//
//        // get a login and pw from a user
//        System.out.println("Enter login: ");
//        String login = input.nextLine();
//
//        System.out.println("Enter password: ");
//        String pw = input.nextLine();
//
////        System.out.printf("%s %s\n", login, pw);
//
//        // see if that user exists in the users db table
//        if(userDAO.checkCredentials(login, pw) == true) {
//            System.out.println("You are logged in!");
//        } else {
//            System.out.println("Invalid creds!!! Calling cops");
//        }
//
//
//        userDAO.closeConnection();
//
//
//        // always close scanner
//        input.close();
//    }
//}
