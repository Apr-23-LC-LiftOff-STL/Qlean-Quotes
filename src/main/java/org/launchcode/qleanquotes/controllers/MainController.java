package org.launchcode.qleanquotes.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.launchcode.qleanquotes.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import javax.validation.Valid;


@Controller
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/payment")
    public String showPayment(){
        return "payment";
    }

//
//    @RequestMapping("/landing")
//    public String shoLandingPage(HttpSession session, Model model) {
//        String customer = (String) session.getAttribute("customer");
//        if (customer == null) {
//            return "redirect:/login";
//        }
//
//        model.addAttribute("customer" , customer);
//        return "landing";
//    }





}
//    @GetMapping("/create-quotes")
//    public String showCreateQuoteForm(Model model) {
//        model.addAttribute("createQuoteDto" , new CreateQuoteFormDTO());
//        return "create-quotes";
//    }
//
//
//    @PostMapping("/create-quotes")
//    public String handleCreateQuoteForm(@ModelAttribute @Valid CreateQuoteFormDTO createQuoteFormDto,
//                                        Errors errors, HttpServletRequest request) {
//        if(errors.hasErrors()){
//            return "/create-quotes";
//        }
//
//        Integer squareFeet = CreateQuoteFormDTO.getSquareFeet();
//        Quote newQuote = new Quote(CreateQuoteFormDTO.getSquareFeet() , CreateQuoteFormDTO.getNumOfRoom(), CreateQuoteFormDTO.getNumOfBathroom());
//        return "create-quotes" ;
//    }




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


//}
