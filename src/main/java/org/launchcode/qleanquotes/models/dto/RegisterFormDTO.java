package org.launchcode.qleanquotes.models.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterFormDTO{

    //Decided not to use phone and address info for register --FOR NOW --

    @NotNull
    @NotBlank(message = "Name is required!")
    @Size(min = 3, max=20, message = "Name must be between 3 and 20 characters!")
    private String name;


    @NotNull
    @NotBlank(message = "Last name is required!")
    @Size(min = 3, max=20, message = "Last name must be between 3 and 20 characters!")
    private String lastName;


    @NotNull
    @NotBlank
    private String email;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 30, message = "Invalid username. Must be between 3 and 20 characters.")
    private String password;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 30, message = "Invalid username. Must be between 3 and 20 characters.")
    private String verifyPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getVerifyPassword(){
        return verifyPassword;
    }

    public void setVerifyPassword(String password){
        this.verifyPassword = verifyPassword;
    }

//phone number
}
