package org.launchcode.qleanquotes.controllers;


import com.squareup.square.models.CreateOrderRequest;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;


@Controller
//@RequestMapping("orders")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    private static final String quoteSessionKey = "quote";

    public static void setQuoteInsession(HttpSession session, Quote quote) {
        session.setAttribute(quoteSessionKey, quote.getId());
    }

    @GetMapping("/createquotes")
    public String showCreateQuoteForm(Model model){
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute(new CreateQuoteFormDTO());
        model.addAttribute("title", "Get Quote");
        return "createquotes";
    }


    @PostMapping("/createquotes")
    public String handleCreateQuoteForm(@ModelAttribute @Valid CreateQuoteFormDTO createQuoteFormDTO,
                                        Errors errors, HttpServletRequest request, Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("errors", errors);

        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "/createquotes";
        }

        double totalCost = 0.0;
        Long totalCharge = 0L;
        if (createQuoteFormDTO.getSquareFeet() != null && createQuoteFormDTO.getNumOfRoom() != null) {
            totalCost += (createQuoteFormDTO.getSquareFeet()) + (createQuoteFormDTO.getNumOfRoom() * 0.01);
            totalCharge += (createQuoteFormDTO.getSquareFeet()) + (createQuoteFormDTO.getNumOfRoom() * 200L);

            if(createQuoteFormDTO.getNumOfBathroom() > createQuoteFormDTO.getNumOfRoom()) {
                errors.rejectValue("numOfBathroom", "bathrooms.tooMany", "You have more bathrooms than total rooms?");
                return "/createquotes";
            }

            if (createQuoteFormDTO.getNumOfBathroom() != null) {
                totalCost += (createQuoteFormDTO.getNumOfBathroom() * 3);
                totalCharge += (createQuoteFormDTO.getNumOfBathroom() * 300L);
            }
            if (createQuoteFormDTO.getCleaningOptions() != null) {
                if (createQuoteFormDTO.getCleaningOptions().equals("deep")) {
                    totalCost += 50;
                    totalCharge += 5000L;
                } else if (createQuoteFormDTO.getCleaningOptions().equals("average")) {
                    totalCost += 25;
                    totalCharge += 2500L;
                }
            }


            model.addAttribute("totalCost", totalCost);
            model.addAttribute("totalCharge", totalCharge);
            System.out.println(totalCost);

            return "createquotes";
        }

        Quote newQuote = new Quote(createQuoteFormDTO.getSquareFeet(), createQuoteFormDTO.getNumOfRoom(), createQuoteFormDTO.getNumOfBathroom(), createQuoteFormDTO.getCleaningOptions(), createQuoteFormDTO.getTotalCost(), createQuoteFormDTO.getTotalCharge());
        quoteRepository.save(newQuote);
        setQuoteInsession(request.getSession(), newQuote);
        model.addAttribute("quote", newQuote);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalCharge", totalCharge);

        return "redirect:/payment";

    }


}
