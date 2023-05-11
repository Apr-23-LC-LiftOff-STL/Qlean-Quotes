package org.launchcode.qleanquotes.services;

import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

    //information about our customers needs to be provided here
    //UserDetailsService requires/enables the loading of user info.

    @Autowired
    private CustomerRepository customerRepository;


    //required method in implementation, below overrides the default
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        Customer customer = customerRepository.findByEmail(username);
//        if (customer == null) {
//            throw new UsernameNotFoundException("User not found!");
//        }
//        return new Customer(customer);
//    }


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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findByEmail(username));
//        Customer customer = customerRepository.findByEmail(username);
//        if (customer == null) {
//            throw new UsernameNotFoundException("User doesn't exist");
//        }
////            customerRepository.save((customer));
//            return customer;
//
//    }

//will below save a user to database everytime they log in tho?
//    public void createUser(UserDetails user) {
//        customerRepository.save((Customer) user);
//    }

}


