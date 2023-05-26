package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfileFormDTO {

    @NotNull
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Please enter a valid phone number (e.g., xxx-xxx-xxxx)")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
