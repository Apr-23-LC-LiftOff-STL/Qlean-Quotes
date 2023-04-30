package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Customer extends AbstractEntity{


    @NotNull
    @NotBlank(message = "Name is required!")
    @Size(min = 3, max=20, message = "Name must be between 3 and 20 characters!")
    private String name;

    @NotNull
    @NotBlank(message = "Last name is required!")
    @Size(min = 3, max=20, message = "Last name must be between 3 and 20 characters!")
    private String lastName;


    @Email(message = "Invalid email.Please try again!")
    @NotNull
    @NotBlank (message = "Email is required")
//    below annotation is to ensure that each email is unique in the database
    @Column(unique = true)
    private String email;


    @NotNull
    private String pwHash;

    @NotNull
    @NotBlank (message = "Address is required")
//    @Embedded to indicate that it is a 'composite object' that should be persisted
//    in the same table as the Customer entity
    @Embedded
    private Address address;

    /* NOTE1 : Mobile phone numbers are not stored as integers, as the integer data type holds values that have the potential to be used in calculations.
     There is no context for using a mobile phone number as part of a calculation, so it is stored as a STRING value. */
    @NotNull
    @NotBlank
    // NOTE2 : Phone numbers have 10 numbers in USA!
    @Size(min = 10, max = 10)
    private String phoneNumber;


    public Customer(){
    }



    public Customer(String name, String lastName, String email, String password, Address address, String phoneNumber){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.address = address;
        this.phoneNumber = phoneNumber;


    }


    // NOT SAVING USER PASSWORD TO DATA!!! HASHING PASSWORD FOR SAFETY!
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password){
        String candidateHash = encoder.encode(password);
        return candidateHash.equals(pwHash);
    }

    //only getters for data
    public String getName(){
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public Address getAddress() {
        return address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //Since address have integer and String info
    @Embeddable
    public class Address {
        private  String streetAddress;
        private int streetNumber;
        private int zipCode;

        public Address(String streetAddress, int streetNumber, int zipCode) {
            this.streetAddress = streetAddress;
            this.streetNumber = streetNumber;
            this.zipCode = zipCode;
        }

        public Address() {

        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public int getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
        }

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }
    }



}
