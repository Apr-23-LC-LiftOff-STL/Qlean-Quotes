package org.launchcode.qleanquotes.models.dto.api.v1;

import org.launchcode.qleanquotes.models.Customer;

public class CustomerApiDTO {

    //maybe wont need id in DTO
    private int id;

    private String email;

    private String name;

    private String lastName;

    public CustomerApiDTO() {}

    public CustomerApiDTO(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
