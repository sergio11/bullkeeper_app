package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Phone Number Not Allowed
 **/
public final class PhoneNumberNotAllowed implements Serializable {

    /**
     * Prefix
     */
    private final String prefix;

    /**
     * Number
     */
    private final String number;

    /**
     * Phone Number
     */
    private final String phoneNumber;


    public PhoneNumberNotAllowed(String prefix, String number, String phoneNumber) {
        this.prefix = prefix;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberNotAllowed(String phoneNumber) {
        this.prefix = "";
        this.number = phoneNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public String toString() {
        return "PhoneNumberNotAllowed{" +
                "prefix='" + prefix + '\'' +
                ", number='" + number + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
