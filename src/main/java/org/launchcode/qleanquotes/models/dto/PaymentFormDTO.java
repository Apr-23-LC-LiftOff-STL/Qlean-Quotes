package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PaymentFormDTO {
    //TODO need to add validation

        @Size(min = 4, max = 50, message="Street address must be longer than 4 characters and shorter than 50")
        private String shippingAddressLine1;
        private String shippingAddressLine2;
        @Size(min = 2, max = 30, message="City must be longer than 4 characters and shorter than 30")
        private String shippingLocality;
        @Size(min = 2, max = 2, message="State must be only 2 characters")
        private String shippingAdministrativeDistrictLevel1;

        @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Zip code should be numeric and be wither 5 digit (ex. 12345) or 9 digit (ex. 12345-6789")
        @Size(min = 5, max = 10, message="Zip code should be between 5 and 10 characters")
        private String shippingPostalCode;
        @Size(min = 4, max = 50, message="Street address must be longer than 4 characters and shorter than 50")
        private String billingAddressLine1;
        private String billingAddressLine2;
        @Size(min = 2, max = 30, message="City must be longer than 4 characters and shorter than 30")
        private String billingLocality;
        @Size(min = 2, max = 2, message="State must be only 2 characters")
        private String billingAdministrativeDistrictLevel1;
        @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Zip code should be numeric and be wither 5 digit (ex. 12345) or 9 digit (ex. 12345-6789")
        @Size(min = 5, max = 10, message="Zip code should be between 5 and 10 characters")
        private String billingPostalCode;
        private String token;
        private String idempotencyKey;

    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getShippingLocality() {
        return shippingLocality;
    }

    public void setShippingLocality(String shippingLocality) {
        this.shippingLocality = shippingLocality;
    }

    public String getShippingAdministrativeDistrictLevel1() {
        return shippingAdministrativeDistrictLevel1;
    }

    public void setShippingAdministrativeDistrictLevel1(String shippingAdministrativeDistrictLevel1) {
        this.shippingAdministrativeDistrictLevel1 = shippingAdministrativeDistrictLevel1;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingLocality() {
        return billingLocality;
    }

    public void setBillingLocality(String billingLocality) {
        this.billingLocality = billingLocality;
    }

    public String getBillingAdministrativeDistrictLevel1() {
        return billingAdministrativeDistrictLevel1;
    }

    public void setBillingAdministrativeDistrictLevel1(String billingAdministrativeDistrictLevel1) {
        this.billingAdministrativeDistrictLevel1 = billingAdministrativeDistrictLevel1;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) { this.token = token;}

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey;}
}
