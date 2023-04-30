package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity{


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    @NotBlank(message = "Name is required!")
    @Size(min = 3, max=20, message = "Name must be between 3 and 20 characters!")
    private String name;

    @Column (name = "last_name")
    @NotNull
    @NotBlank(message = "Last name is required!")
    @Size(min = 3, max=20, message = "Last name must be between 3 and 20 characters!")
    private String lastName;


    //    @Email(message = "Invalid email.Please try again!")
    @Column(name= "email")
    @NotNull
    @NotBlank
    private String email;


    @Column(name = "password_hash")
    @NotNull(message = "This field can not be empty.")
    private String pwHash;



    /* NOTE1 : Mobile phone numbers are not stored as integers, as the integer data type holds values that have the potential to be used in calculations.
    There is no context for using a mobile phone number as part of a calculation, so it is stored as a STRING value. */
    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    // NOTE2 : Phone numbers have 10 numbers in USA!
    @Size(min = 10, max = 10)
    private String phoneNumber;


    @Column(name = "address")
    @NotNull
    @NotBlank (message = "Address is required")
    private Address address;



    // NOT SAVING USER PASSWORD TO DATA!!! HASHING PASSWORD FOR SAFETY!
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Customer(){
    }


    public Customer(String name, String lastName, String email, String password, String phoneNumber,Address address){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public Customer(String email, String password) {
        super();
    }

    public boolean isMatchingPassword(String password){
        String candidateHash = encoder.encode(password);
        return candidateHash.equals(pwHash);
    }

    //we only need getters
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



    //Since address have integar and String info
    public class Address {
        private  String streetAddress;
        private int streetNumber;
        private int zipCode;

        public Address(String streetAddress, int streetNumber, int zipCode) {
            this.streetAddress = streetAddress;
            this.streetNumber = streetNumber;
            this.zipCode = zipCode;
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
