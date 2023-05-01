package controllers;

import models.Customer;
import models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.Errors;
//import javax.validation.Valid;

import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public  String index() {
        return "index";
    }

//    @RequestMapping("")
//    public String index(Model model) {
//        model.addAttribute("customers", customerRepository.findAll());
//        return "index";
//    }
//
//    @GetMapping("/add")
//    public String displaySignUpForm(Model model) {
//        model.addAttribute("title", "Add Customer");
//        model.addAttribute(new Customer());
//        return "add";
//    }
//
//    @PostMapping("/register")
//    public String processAddEmployerForm(@ModelAttribute Customer newCustomer,
//                                         Errors errors) {
//        if (errors.hasErrors()) {
//            return "register";
//        }
//
//        customerRepository.save(newCustomer);
//        return "redirect:";
//
//    }

//        //this validation is obviously silly, we need to change it so that it blocks the same email from signing up twice
//        customerRepository.findAll().forEach(customer -> {
//            if (Objects.equals(customer.getName(), newCustomer.getName())) {
//                errors.rejectValue("name", "invalid", "This customer already exists.");
//            }
//        });
//
//        if (errors.hasErrors()) {
//            return "add";
//        }

        //add catch error in case database fails to save. Alert user that failed to save due to network error and to try again later.
        //you can test this error by temporarily setting savedEntity = null.
        //our error message isn't ideal at present, it shows under the house.name value rather than at the top as a global error. Struggling to fix it.
//        Customer savedEntity = customerRepository.save(newCustomer);
//        if (savedEntity == null) {
//            errors.rejectValue("name", "invalid", "We are having network issues, please try again later.");
//        }
//
//        if (errors.hasErrors()) {
//            return "add";
//        }
//
//
//        customerRepository.save(newCustomer);
//        return "redirect:";
//    }


}
