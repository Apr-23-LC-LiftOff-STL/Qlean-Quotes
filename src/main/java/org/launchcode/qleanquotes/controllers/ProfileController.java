package org.launchcode.qleanquotes.controllers;

import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String displayProfilePage(Model model, RegisterFormDTO registerFormDTO) {
        model.addAttribute((new RegisterFormDTO()));
        model.addAttribute("title", "profile");
        return "profile";
    }
}
