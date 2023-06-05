package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfileFormDTO {

    @NotNull
    @Size(min = 10, max = 11, message="Phone number must be at least 10 digits, without dashes")
    private String phoneNumber;

    @NotNull
    @Size(min = 4, max = 50, message="Street address must be longer than 4 characters and shorter than 50")
    private String street;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
