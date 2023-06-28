package org.launchcode.qleanquotes.controllers;


import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.ProfileFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProfileController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String displayProfilePage(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
//        model.addAttribute(new ProfileFormDTO());
        model.addAttribute("profileFormDTO", new ProfileFormDTO());
        model.addAttribute("title", "profile");
        return "profile";
    }


//    @PostMapping("/profile")
//    public String savePhoneNumber(@ModelAttribute @Valid ProfileFormDTO phoneNumberForm, Errors errors, Model model) {
//        Customer existingCustomer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("phoneNumber", phoneNumberForm.getPhoneNumber());
//        existingCustomer.setPhoneNumber(phoneNumberForm.getPhoneNumber());
//
//        model.addAttribute("street", phoneNumberForm.getStreet());
//        existingCustomer.setStreet(phoneNumberForm.getStreet());
//        model.addAttribute("city", phoneNumberForm.getCity());
//        existingCustomer.setCity(phoneNumberForm.getCity());
//        model.addAttribute("zip", phoneNumberForm.getZip());
//        existingCustomer.setZip(phoneNumberForm.getZip());
//
//        if (errors.hasErrors()) {
//            model.addAttribute("customer", existingCustomer);
//            return "profile";
//        }
//        customerRepository.save(existingCustomer);
//
//        return "redirect:/updated-profile";
//    }

    @PostMapping("/profile")
    public String saveProfileInfo(@ModelAttribute @Valid ProfileFormDTO profileForm, Errors errors, Model model) {
        Customer existingCustomer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", existingCustomer);

        if (errors.hasErrors()) {
            return "profile";
        }

        MultipartFile profileImage = profileForm.getProfileImage();

        if (!profileImage.isEmpty()) {
            try {
                byte[] imageBytes = profileImage.getBytes();
                existingCustomer.setProfileImage(imageBytes); // Set the image bytes to the customer entity
                // Save the image bytes or process it as required
            } catch (IOException e) {
                // Handle the exception
            }
        }
        existingCustomer.setPhoneNumber(profileForm.getPhoneNumber());
        existingCustomer.setStreet(profileForm.getStreet());
        existingCustomer.setCity(profileForm.getCity());
        existingCustomer.setZip(profileForm.getZip());

        customerRepository.save(existingCustomer);

        return "redirect:/updated-profile";
    }

    //
//    @GetMapping("/updated-profile")
//    public String displayUpdatedProfile(Model model) {
//        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("customer", customer);
//        model.addAttribute("customerName", customer.getName());
//        model.addAttribute("phoneNumber", customer.getPhoneNumber());
//        model.addAttribute(new ProfileFormDTO());
//        model.addAttribute("title", "profile");
//
//        return "updated-profile";
//    }

    @GetMapping("/updated-profile")
    public String displayUpdatedProfile(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("phoneNumber", customer.getPhoneNumber());
        model.addAttribute("profileForm", new ProfileFormDTO()); // Updated line
        model.addAttribute("title", "profile");

        return "updated-profile";
    }

    @GetMapping("/profile-image/{customerId}")
    public ResponseEntity<byte[]> displayProfileImage(@PathVariable("customerId") int customerId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (customer != null && customer.getProfileImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(customer.getProfileImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}






