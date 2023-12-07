package contacts_manager;


import contacts_manager.dao.FileContactsDAO;
import contacts_manager.utils.Input;
import contacts_manager.dao.ContactsDAO;
import contacts_manager.dao.MySQLContactsDAO;
import contacts_manager.models.Contact;

import java.util.List;


public class App {

    public static void main(String[] args) {


        ContactsDAO dao = new MySQLContactsDAO();
        Input input = new Input();



        boolean exit = false;
            while (!exit) {
                // Menu
                System.out.print("1. View contacts.\n" + "2. Add a new contact.\n" + "3. Search a contact by name.\n" + "4. Delete an existing contact.\n" + "5. Exit.\n" + "Enter an option (1, 2, 3, 4 or 5):\n");

//                String userInput = input.nextLine;

                int option = input.getInt(1, 5);
                //Switch/Case
                switch (option) {
                    case 1:
                        List<Contact> contacts = dao.fetchContacts();
                        MySQLContactsDAO.outputList(contacts);
//                        System.out.println(contacts);
                        break;
                    case 2:
//                    addContact();
                        String fn = Input.getString("Enter full name: ");
                        String phone = Input.getString("Enter phone number: ");
                        Contact contact = new Contact(fn, phone);
                        dao.insertContact(contact);
                        break;
                    case 3:
//                    currentContacts = dao.searchContacts();
                        String term = Input.getString("Enter the name to search for: ");
                        List<Contact> contacts1 = dao.searchContacts(term);
                        System.out.println(contacts1);
                        break;
                    case 4:
//                    dao.deleteByName();
                        String remove = Input.getString("Enter the name to delete: ");
                        dao.deleteByName(remove);
//                    System.out.println("Contact deleted.");
                        break;
                    case 5:
                        dao.close();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                        break;
                }
            }
        }

    }
