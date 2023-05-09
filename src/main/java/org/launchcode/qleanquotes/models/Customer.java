package org.launchcode.qleanquotes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//TODO there needs to be persistence annotations in this model for the databases tables to relate to each other (foreign key! think @manytoone, @onetomany, blah blah)
@Entity
public class Customer extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String pwHash;

    //below BCrypt class is provided by the spring-security-crypto dependency. It hashes the passwords for us.
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //constructors
    public Customer() {
    }

    public Customer(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }




        public String getName() { return name;}
        public String getLastName() {return lastName;}

        public String getEmail() { return email;}
        public String getPhone() { return phone; }




        // NOT SAVING USER PASSWORD TO DATA!!! HASHING PASSWORD FOR SAFETY!
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        public boolean isMatchingPassword(String password) {
            return encoder.matches(password, pwHash);
        }


    }




     /* NOTE1 : Mobile phone numbers are not stored as integers, as the integer data type holds values that have the potential to be used in calculations.
    There is no context for using a mobile phone number as part of a calculation, so it is stored as a STRING value. */
//    @Column(name = "phone_number")
//    @NotNull
//    @NotBlank
//    // NOTE2 : Phone numbers have 10 numbers in USA!
//    @Size(min = 10, max = 10)
//    private String phoneNumber;
//
//
//    @Column(name = "address")
//    @NotNull
//    @NotBlank (message = "Address is required")
//    private Address address;


        //Since address have integar and String info
//    public class Address {
//        private  String address;
//        private String city;
//        private String state;
//        private int zip;
//
//        public Address(String address, String city, String state, String zip) {
//            this.address = address;
//            this.city = city;
//            this.state = state;
//            this.zip = zip;
//        }
//
//        public String getStreetAddress() {
//            return streetAddress;
//        }
//
//        public void setStreetAddress(String streetAddress) {
//            this.streetAddress = streetAddress;
//        }
//
//        public int getStreetNumber() {
//            return streetNumber;
//        }
//
//        public void setStreetNumber(int streetNumber) {
//            this.streetNumber = streetNumber;
//        }
//
//        public int getZipCode() {
//            return zipCode;
//        }
//
//        public void setZipCode(int zipCode) {
//            this.zipCode = zipCode;
//        }
//    }



