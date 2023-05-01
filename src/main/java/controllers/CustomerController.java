package controllers;


import models.Customer;
import models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



/* NOTE1: The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation.
 Response from a web application is generally view (HTML + CSS + JavaScript) */

/* NOTE2 :@CrossOrigin(origins = "http://localhost:4200") is a Java annotation that can be used in a Spring Boot application to allow
cross-origin requests from a specified origin.*/

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("customers")

@Controller
public class CustomerController {

//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @GetMapping("")
//    public Iterable<Customer>  getCustomer(){
//        return customerRepository.findAll();
//    }
//
//
//    @PostMapping("/register")
//    void addCustomer(@RequestBody Customer customer){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Customer addCustomer = new Customer(customer.getName(), customer.getLastName(), customer.getEmail(),
//                encoder.encode(customer.getPwHash()), customer.getPhoneNumber(), customer.getAddress());
//        customerRepository.save(addCustomer);
//    }

//    @PostMapping("authenticate")
//    public boolean customerAuthenticate(@RequestBody Customer customer) {
//
//        Optional<Customer> customerData = customerRepository.findById(customer.getId());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        if(customerData.isPresent()) {
//            Customer customerInfo = customerData.get();
//            if(encoder.matches(customer.getPwHash(), customerInfo.getPwHash())){
//                return true;
//            } else {
//                return false;
//            }
//        }else{
//            return false;
//        }
//    }



}
