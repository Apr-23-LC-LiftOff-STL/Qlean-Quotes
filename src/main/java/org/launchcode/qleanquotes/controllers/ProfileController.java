package org.launchcode.qleanquotes.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.ProfileFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
    public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String displayProfilePage(Model model, RegisterFormDTO registerFormDTO, ProfileFormDTO profileFormDTO) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute(new ProfileFormDTO());
        model.addAttribute(registerFormDTO);
        model.addAttribute(profileFormDTO);
        model.addAttribute("title", "Profile");
        return "profile";
    }

//    @PostMapping("/profile")
//    public  String processProfileForm(@ModelAttribute @Valid ProfileFormDTO profileFormDTO, RegisterFormDTO registerFormDTO, Errors errors,
//                                       HttpServletRequest request){
//        if(errors.hasErrors()){
//            return "/profile";
//        }
//        Customer existingCustomer = customerRepository.findByEmail(registerFormDTO.getEmail());
//
//        //We have existing customer, has name, last name, email, and password. should have an empty phone number field.
//        //we want to figure out how to add phone number involving profileFormDTO somehow
//        //will also save new phone to customer repository somehow
////        customerRepository.save();
//
//        return "redirect:/";
//    }


}
//    @GetMapping("/profile")
//    public String displayProfilePage(Model model, RegisterFormDTO registerFormDTO) {
//        ProfileFormDTO profileFormDTO = new ProfileFormDTO();
//        model.addAttribute(new RegisterFormDTO());
//        model.addAttribute("registerForm", registerFormDTO);
//        model.addAttribute("profileForm", profileFormDTO);
//        model.addAttribute("title", "Profile");
//        return "profile";


//This is the one that works
//    @GetMapping("/profile")
//    public String displayProfilePage(Model model, RegisterFormDTO registerFormDTO) {
//        model.addAttribute(new RegisterFormDTO());
//        model.addAttribute("title", "Profile");
//        return "profile";


