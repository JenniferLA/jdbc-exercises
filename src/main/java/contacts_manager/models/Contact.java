package contacts_manager.models;

import lombok.Getter;
import lombok.Setter;

public class Contact {
    // Getter/Setter
    @Getter
    @Setter

    private String name;
    private String number;

    // Constructor for name and number
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Contact() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return formatPhoneNumber(number);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String formatPhoneNumber(String phoneNumber) {
        // Remove all non-digit characters from the phone number
        String digitsOnly = phoneNumber.replaceAll("\\D", "");

        // Check if the number has the required length for formatting
        if (digitsOnly.length() == 10) {
            // Format number with dashes (###) ###-####
            return String.format("(%s) %s-%s",
                    digitsOnly.substring(0, 3),
                    digitsOnly.substring(3, 6),
                    digitsOnly.substring(6));
        } else {
            // If the number doesn't have 10 digits, return the original number
            return phoneNumber;
        }
    }
    public String toString(){
        return "\n" + name + "|" + number;
    }
}
