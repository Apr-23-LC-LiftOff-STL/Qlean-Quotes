package models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User extends AbstractEntity{


    @NotNull
    @NotBlank(message = "User name is required!")
    @Size(min = 3, max=20, message = "Name must be between 3 and 20 characters!")
    private String name;

    @NotNull
    @NotBlank(message = "Last name is required!")
    @Size(min = 3, max=20, message = "Last name must be between 3 and 20 characters!")
    private String lastName;


    @Email(message = "Invalid email.Please try again!")
    @NotNull
    @NotBlank
    private String email;


    @NotNull
    private String pwHash;

    /* NOTE1 : Mobile phone numbers are not stored as integers, as the integer data type holds values that have the potential to be used in calculations.
     There is no context for using a mobile phone number as part of a calculation, so it is stored as a STRING value. */
    @NotNull
    @NotBlank
    // NOTE2 : Phone numbers have 10 numbers in USA!
    @Size(min = 10, max = 10)
    private String phoneNumber;


    public User(){
    }


    public User(String name, String lastName, String email, String password, String phoneNumber){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwHash = encoder.encode(password);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }



}
