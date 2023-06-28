package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//profile image import
import org.springframework.web.multipart.MultipartFile;

public class ProfileFormDTO {

    @Pattern(regexp = "^(?:\\d{3}-?){1,2}\\d{4}$", message = "Phone number must be numeric and may include dashes, Ex: 123-123-1234")
    @Size(min = 10, max = 12, message="Phone number must be at least 10 digits and may not exceed 11")
    private String phoneNumber;

    @Size(min = 4, max = 50, message="Street address must be longer than 4 characters and shorter than 50")
    private String street;

    @Size(min = 4, max = 50, message="City must be longer than 4 characters and shorter than 50")
    private String city;

    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Zip code should be numeric and be wither 5 digit (ex. 12345) or 9 digit (ex. 12345-6789")
    @Size(min = 5, max = 10, message="Zip code should be between 5 and 10 characters")
    private String zip;

//    @Size(max = 1048576, message = "Profile image size should not exceed 1MB")
    private MultipartFile profileImage;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


//    getters and setters for profile image
    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }
}
