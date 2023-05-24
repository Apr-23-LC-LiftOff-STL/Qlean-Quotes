package org.launchcode.qleanquotes.services;


import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomerDetailsService implements UserDetailsService {

    //UserDetailsService requires/enables the loading of user info.

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);

        if (customer == null) {
            throw new UsernameNotFoundException("Could not find customer");
        }
        return customer;
//        return new Customer(customer);
    }
}


