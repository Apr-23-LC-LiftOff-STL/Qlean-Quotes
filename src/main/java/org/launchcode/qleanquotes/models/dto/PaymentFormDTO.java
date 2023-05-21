package org.launchcode.qleanquotes.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentFormDTO {
        @NotNull
        @NotBlank
        private String shippingAddressLine1;
        private String shippingAddressLine2;
        @NotNull
        @NotBlank
        private String shippingLocality;
        @NotNull
        @NotBlank
        private String shippingAdministrativeDistrictLevel1;
        @NotNull
        @NotBlank
        private String shippingPostalCode;
        @NotNull
        @NotBlank
        private String billingAddressLine1;
        private String billingAddressLine2;
        @NotNull
        @NotBlank
        private String billingLocality;
        @NotNull
        @NotBlank
        private String billingAdministrativeDistrictLevel1;
        @NotNull
        @NotBlank
        private String billingPostalCode;

    public PaymentFormDTO (){}

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
}
