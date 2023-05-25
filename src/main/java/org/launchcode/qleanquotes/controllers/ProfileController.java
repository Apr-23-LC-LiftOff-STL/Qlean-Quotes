package org.launchcode.qleanquotes.controllers;


import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.launchcode.qleanquotes.services.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import java.security.Principal;


@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String displayProfilePage(Model model) {
        Customer customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("title", "profile");
        return "profile";
    }

}


//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String customerEmail = authentication.getName();
//    Customer customer = customerRepository.findByEmail(customerEmail);
//        model.addAttribute("customerName", customer.getName());
//                model.addAttribute("customer", customer);
//                model.addAttribute("title", "profile");
//                return "profile";

//
////    @GetMapping("/profile")
////    public String displayProfilePage(Model model ){}
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String customerEmail = authentication.getName();
////        Customer customerDetails = (Customer) customerDetailsService.loadUserByUsername(customerEmail);
////        if (customerDetails == null) {
////            // Customer not found, handle the error
////            // For example, you can redirect to an error page or display a generic message
////            return "error";
////        }
////
////
//////        Customer customer = customerDetails.getName();
////        model.addAttribute("customerName", customerDetails.getName());
////        model.addAttribute("title", "profile");
////        return "profile";
//
////        Customer currentCustomer = customerRepository.findByEmail(customer.email());
////        Customer customer = customerRepository.getCustomer();
////        model.addAttribute((new RegisterFormDTO()));
////        model.addAttribute("customer", currentCustomer);
////        model.addAttribute("title", "profile");
////        return "profile";
//    }



