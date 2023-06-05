package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfileFormDTO {

    @Pattern(regexp = "^(?:\\d{3}-?){1,2}\\d{4}$", message = "Phone number must be numeric and may include dashes, Ex: 123-123-1234")
    @Size(min = 10, max = 12, message="Phone number must be at least 10 digits and may not exceed 11")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
