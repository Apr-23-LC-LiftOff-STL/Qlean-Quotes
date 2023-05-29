package org.launchcode.qleanquotes.controllers;


import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.dto.ProfileFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

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
    public String savePhoneNumber(@ModelAttribute("phoneNumber") ProfileFormDTO phoneNumberForm, Model model) {
        model.addAttribute("phoneNumber", phoneNumberForm.getPhoneNumber());
        return "redirect:/updated-profile";
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






