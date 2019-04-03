package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Contact DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ContactDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Local Id
     */
    @JsonProperty("local_id")
    private String localId;

    /**
     * Photo Encoded String
     */
    @JsonProperty("photo_encoded_string")
    private String photoEncodedString;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    /**
     * Is Blocked
     */
    @JsonProperty("is_blocked")
    private boolean isBlocked;

    /**
     * Phone List
     */
    @JsonProperty("phone_list")
    private Iterable<PhoneContactDTO> phoneList;

    /**
     * Email List
     */
    @JsonProperty("email_list")
    private Iterable<EmailContactDTO> emailList;

    /**
     * Address List
     */
    @JsonProperty("address_list")
    private Iterable<PostalAddressDTO> addressList;

    public ContactDTO() {}

    public ContactDTO(String identity, String name, String localId, String photoEncodedString, String kid, String terminal, boolean isBlocked, Iterable<PhoneContactDTO> phoneList, Iterable<EmailContactDTO> emailList, Iterable<PostalAddressDTO> addressList) {
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

    public Iterable<PhoneContactDTO> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(Iterable<PhoneContactDTO> phoneList) {
        this.phoneList = phoneList;
    }

    public Iterable<EmailContactDTO> getEmailList() {
        return emailList;
    }

    public void setEmailList(Iterable<EmailContactDTO> emailList) {
        this.emailList = emailList;
    }

    public Iterable<PostalAddressDTO> getAddressList() {
        return addressList;
    }

    public void setAddressList(Iterable<PostalAddressDTO> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PhoneContactDTO {

        @JsonProperty("phone")
        private String phone;

        public PhoneContactDTO() {}

        public PhoneContactDTO(String phone) {
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
            return "PhoneContactDTO [phone=" + phone + "]";
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmailContactDTO {

        /**
         * Email
         */
        @JsonProperty("email")
        private String email;

        public EmailContactDTO() {}

        public EmailContactDTO(final String email) {
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
            return "EmailContactDTO [email=" + email + "]";
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostalAddressDTO {

        /**
         * City
         */
        @JsonProperty("city")
        private String city;

        /**
         * State
         */
        @JsonProperty("state")
        private String state;

        /**
         * Country
         */
        @JsonProperty("country")
        private String country;

        public PostalAddressDTO() {}

        /**
         *
         * @param city
         * @param state
         * @param country
         */
        public PostalAddressDTO(final String city, final String state, final String country) {
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
            return "PostalAddressDTO [city=" + city + ", state=" + state + ", country=" + country + "]";
        }
    }
}
