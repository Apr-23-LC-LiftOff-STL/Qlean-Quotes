package org.launchcode.qleanquotes.controllers;

import org.launchcode.qleanquotes.models.Customer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @GetMapping("")
    public String displayIndexPage(Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("title", "landing");
        return "index";
    }
}
