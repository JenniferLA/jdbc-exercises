package contacts_manager.dao;

import contacts_manager.models.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileContactsDAO implements ContactsDAO {
    //    private Path pathToContacts = null;
    Path pathToContacts = Paths.get("contacts.txt");

    @Override
    public List<Contact> fetchContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            List<String> contactsFromFile = Files.readAllLines(pathToContacts);
            for (String stringContact : contactsFromFile) {
                String[] contactData = stringContact.split("\\|");
                if (contactData.length >= 2) {
                    String name = contactData[0].trim();
                    String number = contactData[1].trim();
                    Contact contact = new Contact(name, number);
                    contacts.add(contact);
                } else {
                    System.out.println("Invalid format in line: " + stringContact);
                }
            }

        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return contacts;
    }


    @Override
    public long insertContact(Contact contact) {
        String contactLine = String.format("%s | %s", contact.getName(), contact.getNumber());
        try {
            Files.writeString(pathToContacts, contact.toString() + "\n", StandardOpenOption.APPEND);
        } catch (IOException iox) {
            System.out.println("Error writing to file " + iox.getMessage());
        }
        return 0;
    }

    @Override
//    public void deleteByName(String name) {
    public void deleteByName(String name) {
        try {
            List<String> lines = Files.readAllLines(pathToContacts);
            List<String> writeLines = new ArrayList<>();
            for (String line : lines) {
                if (!line.toUpperCase().startsWith(name.toUpperCase())){
                    writeLines.add(line);
                }
            }
            Files.write(pathToContacts, writeLines);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    @Override
    public List<Contact> searchContacts(String searchTerm) {
        try {
            List<String> lines = Files.readAllLines(pathToContacts);
            for (String line : lines) {
                if (line.toLowerCase().startsWith(searchTerm.toLowerCase())){
                    System.out.println(line);
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return null;
    }

    @Override
    public void open() {
//        pathToContacts = Paths.get("contacts.txt");
    }

    @Override
    public void close() {

    }
}
