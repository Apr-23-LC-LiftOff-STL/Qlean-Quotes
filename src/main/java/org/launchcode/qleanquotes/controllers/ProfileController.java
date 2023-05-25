package org.launchcode.qleanquotes.controllers;


import org.launchcode.qleanquotes.models.Customer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String displayProfilePage(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("title", "profile");
        return "profile";
    }

}




