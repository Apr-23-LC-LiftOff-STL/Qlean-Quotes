package org.launchcode.qleanquotes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.validation.constraints.Pattern;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer extends AbstractEntity implements UserDetails {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;



    //below BCrypt class is provided by the spring-security-crypto dependency. It hashes the passwords for us.
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Customer() {
    }

    public Customer(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = encoder.encode(password);
    }

    public String getName() {
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //below nonsense is required by the UserDetails implementation or for security, dont touch, plz.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //the transient annotation means the authorities field will not be persisted in the database
    //initializes authorities to an empty set
    @Transient
    Collection<? extends GrantedAuthority> authorities = Collections.emptySet();

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
