package org.launchcode.qleanquotes.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.LoginFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import java.util.Optional;


@Controller
public class AuthenticationController {


    @Autowired
    CustomerRepository customerRepository;

    private static final String customerSessionKey = "customer";

    // Stores key/value pair with session key and customer ID
    public static void setCustomerInSession(HttpSession session, Customer customer) {
        session.setAttribute(customerSessionKey, customer.getId());
    }

    // Look up customer with key
    public Customer getCustomerFromSession(HttpSession session){
        Integer customerId = (Integer) session.getAttribute(customerSessionKey);
        if(customerId == null){
            return null;
        }

        Optional <Customer> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()){
            return  null;
        }

        return customer.get();
    }


    // Handlers for registration form
    @GetMapping("/register")
    public String displayRegisterForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        //model.addAttribute("title", "Register");
        return "register";
    }


    @PostMapping("/register")
    public  String processRegisterForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors,
                                       HttpServletRequest request){
        if(errors.hasErrors()){
            return "/register";
        }
        Customer existingCustomer = customerRepository.findByEmail(registerFormDTO.getEmail());

        if(existingCustomer != null){
            errors.rejectValue("email", "email.alreadyexists", "A user with that email already exists");
            return "register";
        }


        // Send customer back to form if passwords didn't match
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if(!password.equals(verifyPassword)){
            errors.rejectValue("password" , "passwords.mismatch", "Passwords do not match");
            return "register";
        }

        // OTHERWISE, save new email , hashed password and other info in database, start a new session, and redirect to home page
        Customer newCustomer = new Customer(registerFormDTO.getName(), registerFormDTO.getLastName(),registerFormDTO.getEmail(),registerFormDTO.getPassword());
        customerRepository.save(newCustomer);
        setCustomerInSession(request.getSession(), newCustomer);
        return "redirect:";
    }

    // Handlers for login form
    @GetMapping("/login")
    public String displayLoginForm(Model model){
        model.addAttribute(new LoginFormDTO());
//        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors,
                                   HttpServletRequest request){

        if(errors.hasErrors()){
            return "login";
        }

        // Look up user in database using email they provided in the form
        Customer theCustomer = customerRepository.findByEmail(loginFormDTO.getEmail());


        if(theCustomer == null){
            errors.rejectValue("email", "customer.invalid", "The given email does not exist");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if(!theCustomer.isMatchingPassword(password)){
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

        setCustomerInSession(request.getSession(), theCustomer);
        return  "redirect:";
    }

    // Handler for logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return  "redirect:/login";
    }






}
