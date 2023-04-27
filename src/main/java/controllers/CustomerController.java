package controllers;



import models.Customer;
import models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



/* NOTE1: The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation.
 Response from a web application is generally view (HTML + CSS + JavaScript) */

/* NOTE2 :@CrossOrigin(origins = "http://localhost:4200") is a Java annotation that can be used in a Spring Boot application to allow
cross-origin requests from a specified origin.*/

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("customers")


public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public Iterable<Customer>  getCustomer(){
        return customerRepository.findAll();
    }


    //getPwHash should already have the password encoded so the encoding
    // function should not be included in the below controls.
    @PostMapping("add")
    void addCustomer(@RequestBody Customer customer){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer addCustomer = new Customer(customer.getName(), customer.getLastName(), customer.getEmail(),
               encoder.encode(customer.getPwHash()), customer.getAddress(), customer.getPhoneNumber());
        customerRepository.save(addCustomer);
    }

    @PostMapping("authenticate")
    public boolean customerAuthenticate(@RequestBody Customer customer) {
        Optional<Customer> customerData = customerRepository.findById(customer.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(customerData.isPresent()) {
            Customer customerInfo = customerData.get();
            if(encoder.matches(customer.getPwHash(), customerInfo.getPwHash())){
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
//this part maybe should be in AuthenticationController...

//also, FEELING LIKE I AM MISSING SOME PARTS ABOUT LOGINFORMDTO AND REGISTERDTO...




}
