package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.launchcode.qleanquotes.models.Customer;
import org.springframework.stereotype.Controller;

public class ProfileFormDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Invalid phone number")
    private String phone;

    public String getPhone() { return phone;}

    public void setPhone(String phone) { this.phone = phone; }
}




