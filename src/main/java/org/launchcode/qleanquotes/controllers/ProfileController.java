package org.launchcode.qleanquotes.controllers;


import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.ProfileFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
@Autowired
private CustomerRepository customerRepository;
    @GetMapping("/profile")
    public String displayProfilePage(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
        model.addAttribute(new ProfileFormDTO());
        model.addAttribute("title", "profile");
        return "profile";
    }


    @PostMapping("/profile")
    public String savePhoneNumber(@ModelAttribute @Valid ProfileFormDTO phoneNumberForm, Errors errors, Model model) {
        Customer existingCustomer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("phoneNumber", phoneNumberForm.getPhoneNumber());
        existingCustomer.setPhoneNumber(phoneNumberForm.getPhoneNumber());
        if (errors.hasErrors()) {
            model.addAttribute("customer", existingCustomer);
            return "profile";
        }
        customerRepository.save(existingCustomer);

        return "redirect:/profile";
//
    }

    @GetMapping("/updated-profile")
    public String displayUpdatedProfile(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("phoneNumber", customer.getPhoneNumber());
        model.addAttribute(new ProfileFormDTO());
        model.addAttribute("title", "profile");

        return "updated-profile";
    }

}






