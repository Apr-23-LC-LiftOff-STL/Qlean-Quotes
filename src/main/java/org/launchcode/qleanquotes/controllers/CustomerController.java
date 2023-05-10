package org.launchcode.qleanquotes.controllers;


import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//
//import org.launchcode.qleanquotes.models.data.CustomerRepository;
//import org.launchcode.qleanquotes.service.CustomerDataServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;

import org.launchcode.qleanquotes.models.Customer;



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
//    @GetMapping("/profile")
//    public String getCustomerProfile(Model model) {
//        Customer customer = customerRepository.findById(1).orElse(null);
//        model.addAttribute("customer", customer);
//        return "profile";
//    }
//
//    @PostMapping("/profile")
//    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
//        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
//        if (existingCustomer == null) {
//            return "error";
//        }
//        Customer updatedCustomer = new Customer(
//                customer.getName(),
//                customer.getLastName(),
//                customer.getEmail(),
////                existingCustomer.getPassword()
//        );
//        customerRepository.save(updatedCustomer);
//        return "success";
//    }
    }




//    @Autowired
//    private CustomerDataService customerDataService;
//    @GetMapping("/profile")
//    public String getCustomer(Model model) {
//        Customer customer = customerDataService.getCustomer();
//        model.addAttribute("customer", customer);
//        return "profile.html";
//    }
//
//    @PostMapping("/profile")
//    public String updateCustomer(@RequestBody Customer newCustomer) {
//        if (customerDataService.updateCustomer(newCustomer) == null) {
//            return "error";
//        }
//        return "success";
//    }







//    @Autowired
//    CustomerDataService customerDataService;
//    @GetMapping("/profile")
//    public String getCustomer() {
//        return "profile";
//@GetMapping("/profile")
//public Customer getCustomer() {
//    return customerDataService.getCustomer();
//}
//
//    @PostMapping("/profile")
//    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody Customer newCustomer) {
//        if ( customerDataService.updateCustomer(newCustomer) == null ) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().build();
//    }


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




