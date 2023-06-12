package org.launchcode.qleanquotes.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/authentication/register")
    public String displayRegisterForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "authentication/register";
    }

    @PostMapping("/authentication/register")
    public String processRegisterForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors,
                                      HttpServletRequest request, Model model) {
        model.addAttribute("errors", errors);
        if (errors.hasErrors()) {
            return "authentication/register";
        }
        Customer existingCustomer = customerRepository.findByEmail(registerFormDTO.getEmail());

        if (existingCustomer != null) {
            errors.rejectValue("email", "email.alreadyExists", "A user with that email already exists");
            return "authentication/register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "authentication/register";
        }

        // OTHERWISE, save new email , hashed password and other info in database, and redirect to home page
        Customer newCustomer = new Customer(registerFormDTO.getName(), registerFormDTO.getLastName(), registerFormDTO.getEmail(), registerFormDTO.getPassword(), "", "", "", "");
        customerRepository.save(newCustomer);
//        TODO figure out why, after registering a new user, it takes them t login, not index, its cause we need to create a session
        return "redirect:";
    }

    @GetMapping("/authentication/login")
    public String displayLoginForm(Model model) {
        return "authentication/login";
    }

}

