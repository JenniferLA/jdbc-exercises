//package contacts_manager;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ContactsUtils {
//
//    Path pathToContacts = Paths.get("contacts.txt");
//
////
////    // 1. Contacts
////    public List<Contact> loadContacts(Path pathToContacts) {
////        List<Contact> contacts = new ArrayList<>();
////        try {
////            List<String> contactsFromFile = Files.readAllLines(pathToContacts);
////
////            for (String stringContact : contactsFromFile) {
////                String[] contactData = stringContact.split("\\|");
////                if (contactData.length >= 2) {
////                    String name = contactData[0].trim();
////                    String number = contactData[1].trim();
////                    Contact contact = new Contact(name, number);
////                    contacts.add(contact);
////                } else {
////                    System.out.println("Invalid format in line: " + stringContact);
////                }
////            }
////
////        } catch (IOException iox) {
////            iox.printStackTrace();
////        }
////        return contacts;
////    }
////    // 2. View Contact List
////    public void outputList(List<Contact> contacts) {
////        System.out.println("Name | Phone number");
////        System.out.println("---------------");
////        for (Contact contact : contacts) {
////            System.out.println(contact.getName() + "|" + contact.getNumber());
////        }
////    }
//
//    // 3. Search Contacts
//    Contact searchContactByName(List<Contact> contacts, String name) {
//        for (Contact contact : contacts) {
//            if (contact.getName().equalsIgnoreCase(name)) {
//                return contact;
//            }
//        }
//        return null;
//    }
//
//    // 4. Remove Contacts
//    Contact deleteContact(List<Contact> contacts, String name) {
//        for (Iterator<Contact> iterator = contacts.iterator(); iterator.hasNext();) {
//            Contact contact = iterator.next();
//            if (contact.getName().equalsIgnoreCase(name)) {
//                iterator.remove();
//                return contact;
//            }
//        }
//        return null;
//    }
//
//    void writeContactsToFile(Path pathToContacts, List<Contact> listToWrite) {
//        List<String> contactsAsString = new ArrayList<>();
//        for (Contact contact : listToWrite) {
//            String contactString = contact.getName() + "|" + contact.getNumber();
//            contactsAsString.add(contactString);
//        }
//
//        try{
//            Files.write(pathToContacts, contactsAsString);
//        } catch (IOException iox){
//            System.out.println(iox.getMessage());
//        }
//    }
//
//    }
//

