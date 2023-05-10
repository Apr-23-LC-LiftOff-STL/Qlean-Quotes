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

    @PostMapping("/register")
    public  String processProfileForm(@ModelAttribute @Valid ProfileFormDTO profileFormDTO, RegisterFormDTO registerFormDTO, Errors errors,
                                       HttpServletRequest request){
        if(errors.hasErrors()){
            return "/profile";
        }
        Customer existingCustomer = customerRepository.findByEmail(registerFormDTO.getEmail());

        //We have existing customer, has name, last name, email, and password. should have an empty phone number field.
        //we want to figure out how to add phone number involving profileFormDTO somehow
        //will also save new phone to customer repository somehow
//        customerRepository.save();

        return "redirect:/profile";
    }
//
//
//        if(existingCustomer != null){
//            errors.rejectValue("email", "email.alreadyexists", "A user with that email already exists");
//            return "/login";
//        }
    // next need post mapping-- when user hits add it will go through post mapping
    // look at authentication controller and see how it
    // create save button in html



//    @PostMapping("/profile")
//    public String processProfilePage(@RequestParam("email") String email, @RequestParam("phone") String phone) {
//        Customer customer = customerRepository.findByEmail(email);
//        customer.setPhone(phone);
//        customerRepository.save(customer);
//        return "redirect:/profile";
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
//    }
//
//    public String displayProfileEditPage(Model model, @RequestParam("email") String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        model.addAttribute("customer", customer);
//        return "profile_edit";
//    }
//
//    @PostMapping("/profile/edit")
//    public String handleProfileEditForm(@RequestParam("email") String email, @RequestParam("phone") String phone) {
//        Customer customer = customerRepository.findByEmail(email);
//        customer.setPhone(phone);
//        customerRepository.save(customer);
//        return "redirect:/profile";
//    }
//



//This is the original that works
//    @GetMapping("/profile")
//    public String displayProfilePage(Model model, RegisterFormDTO registerFormDTO) {
//        model.addAttribute(new RegisterFormDTO());
//        model.addAttribute("title", "Profile");
//        return "profile";
//    }
//I'm not sure where this one came from
//    @GetMapping("/profile/edit")
//    public String displayProfileEditPage(Model model) {
//        model.addAttribute("profileFormDTO", new ProfileFormDTO());
//        return "profile_edit";
//    }
//}


//    @GetMapping("/profile")
//    public String displayProfilePage(Model model, @RequestParam("email") String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        model.addAttribute("customer", customer);
//        model.addAttribute("profileFormDTO", new ProfileFormDTO());
//        model.addAttribute("title", "Profile");
//        return "profile";
//    }
//
//    @GetMapping("/profile/edit")
//    public String displayProfileEditPage(Model model, @RequestParam("email") String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        model.addAttribute("customer", customer);
//        model.addAttribute("profileFormDTO", new ProfileFormDTO());
//        return "profile_edit";
//    }
//
//    @PostMapping("/profile/edit")
//    public String handleProfileEditForm(@ModelAttribute("profileFormDTO") ProfileFormDTO formDTO, @RequestParam("email") String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        customer.setAddress(formDTO.getAddress());
//        customer.setPhoneNumber(formDTO.getPhoneNumber());
//        customerRepository.save(customer);
//        return "redirect:/profile";
//    }
//}

//   @PostMapping("/profile/edit")
//    public String handleProfileEditForm(@ModelAttribute("profileFormDTO") ProfileFormDTO formDTO,
//                                        @RequestParam("email") String email) {
//        Customer customer = customerRepository.findByEmail(email);
//       ProfileFormDTO addressDTO = formDTO.getAddressDTO();
//       customer.setAddress(addressDTO.getAddress());
//       customer.setPhoneNumber(addressDTO.getPhoneNumber());
//
////       customer.setAddress(formDTO.getAddress());
////       customer.setPhoneNumber(formDTO.getPhoneNumber());
//       customerRepository.save(customer);
//       return "redirect:/profile";



    //Note that you will need to instantiate the encoder variable with BCryptPasswordEncoder in your ProfileController.







// Update the customer information

//        // Update the customer information
//            customer.setName(formDTO.getName());
//            customer.setLastName(formDTO.getLastName());
//            customer.setEmail(formDTO.getEmail());
//            customerRepository.save(customer);
//
//            model.addAttribute("message", "Your profile has been updated successfully.");
//            return "profile";



