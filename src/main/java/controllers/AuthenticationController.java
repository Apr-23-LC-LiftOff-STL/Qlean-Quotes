package controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import models.Customer;
import models.data.CustomerRepository;
import models.dto.LoginFormDTO;
import models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class AuthenticationController {


    @Autowired
    CustomerRepository customerRepository;

    private static final String customerSessionKey = "customer";

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

    public static void setCustomerRepository(HttpSession session, Customer customer) {
        session.setAttribute(customerSessionKey, customer.getId());
    }

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @GetMapping("register")
    public String displayRegisterForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }


    @PostMapping("register")
    public  String processRegisterForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors,
                                       HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }
        Customer existingCustomer = customerRepository.findByEmail(registerFormDTO.getEmail());

        if (existingCustomer != null) {
            errors.rejectValue("email", "email.already-exist", "A user with that email already exists");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        //ESRA, i have commented out the below section because i beleive there is an issue with the constructor
        //the program couldnt finish the bootRun with it so i had to comment it out and add a nonsense return
//        statement just to get it to a point where the site can be locally served.
        //i dont wanna touch it too much, i figure youre the best candidate to patch it up.

//        Customer newCustomer = new Customer(registerFormDTO.getEmail(), registerFormDTO.getPassword());
//        customerRepository.save(newCustomer);
//        getCustomerFromSession(request.getSession());
//        return "redirect:";
        return password;
    }


    @GetMapping("login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors,
                                   HttpServletRequest request, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Log In");
            return "login";
        }

        Customer theCustomer = customerRepository.findByEmail(loginFormDTO.getEmail());

        if(theCustomer == null){
            errors.rejectValue("email", "user.invalid", "The given email does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if(!theCustomer.isMatchingPassword(password)){
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setCustomerRepository(request.getSession(), theCustomer);
        return  "redirect:";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return  "redirect:/login";
    }



}