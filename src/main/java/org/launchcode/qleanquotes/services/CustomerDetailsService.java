package org.launchcode.qleanquotes.services;

import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerDetailsService implements UserDetailsService {

    //information about our customers needs to be provided here
    @Autowired
    CustomerRepository customerRepository;

//    @Autowired
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void saveUser(Customer user) {
//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        customerRepository.save(user);
//    }

//    @Override
//    public List<Object> isUserPresent(Customer customer) {
//        boolean userExists = false;
//        String message = null;
//        Optional<Customer> existingUserEmail = Optional.ofNullable(customerRepository.findByEmail(customer.getEmail()));
//        if (existingUserEmail.isPresent()) {
//            userExists = true;
//            message = "Email Already Present!";
//        }
//        if (existingUserEmail.isPresent()) {
//            message = "User Already Exists!";
//        }
//        System.out.println("existingUserEmail.isPresent() - " + existingUserEmail.isPresent());
//        return Arrays.asList(userExists, message);
//    }


    //required method in implementation, below overrides the default
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findByEmail(username));
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new Customer(customer);
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }
}
