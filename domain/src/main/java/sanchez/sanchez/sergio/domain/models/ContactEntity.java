package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Contact Entity
 */
public class ContactEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Name
     */
    private String name;


    /**
     * Local Id
     */
    private String localId;

    /**
     * Photo Encoded String
     */
    private String photoEncodedString;

    /**
     * Kid
     */
    private String kid;

    /**
     * Terminal
     */
    private String terminal;

    /**
     * Is Blocked
     */
    private boolean isBlocked;

    /**
     * Phone List
     */
    private Iterable<PhoneContactEntity> phoneList;

    /**
     * Email List
     */
    private Iterable<EmailContactEntity> emailList;

    /**
     * Address List
     */
    private Iterable<PostalAddressEntity> addressList;


    public ContactEntity(){}

    public ContactEntity(String identity, String name, String localId, String photoEncodedString, String kid, String terminal, boolean isBlocked, Iterable<PhoneContactEntity> phoneList, Iterable<EmailContactEntity> emailList, Iterable<PostalAddressEntity> addressList) {
        this.identity = identity;
        this.name = name;
        this.localId = localId;
        this.photoEncodedString = photoEncodedString;
        this.kid = kid;
        this.terminal = terminal;
        this.isBlocked = isBlocked;
        this.phoneList = phoneList;
        this.emailList = emailList;
        this.addressList = addressList;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getPhotoEncodedString() {
        return photoEncodedString;
    }

    public void setPhotoEncodedString(String photoEncodedString) {
        this.photoEncodedString = photoEncodedString;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Iterable<PhoneContactEntity> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(Iterable<PhoneContactEntity> phoneList) {
        this.phoneList = phoneList;
    }

    public Iterable<EmailContactEntity> getEmailList() {
        return emailList;
    }

    public void setEmailList(Iterable<EmailContactEntity> emailList) {
        this.emailList = emailList;
    }

    public Iterable<PostalAddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(Iterable<PostalAddressEntity> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", localId='" + localId + '\'' +
                ", photoEncodedString='" + photoEncodedString + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", isBlocked=" + isBlocked +
                ", phoneList=" + phoneList +
                ", emailList=" + emailList +
                ", addressList=" + addressList +
                '}';
    }

    /**
     * Phone Contact
     * @author ssanchez
     *
     */
    public static class PhoneContactEntity {

        private String phone;

        public PhoneContactEntity() {}

        public PhoneContactEntity(String phone) {
            super();
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "PhoneContactEntity [phone=" + phone + "]";
        }

    }

    public static class EmailContactEntity {

        /**
         * Email
         */
        private String email;

        public EmailContactEntity() {}

        public EmailContactEntity(final String email) {
            super();
            this.email = email;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "EmailContactEntity [email=" + email + "]";
        }

    }

    public static class PostalAddressEntity {

        /**
         * City
         */
        private String city;

        /**
         * State
         */
        private String state;

        /**
         * Country
         */
        private String country;

        public PostalAddressEntity() {}

        /**
         *
         * @param city
         * @param state
         * @param country
         */
        public PostalAddressEntity(final String city, final String state, final String country) {
            super();
            this.city = city;
            this.state = state;
            this.country = country;
        }



        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "PostalAddressEntity [city=" + city + ", state=" + state + ", country=" + country + "]";
        }
    }
}
