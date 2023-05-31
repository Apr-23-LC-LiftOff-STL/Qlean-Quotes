package org.launchcode.qleanquotes.controllers;


import com.squareup.square.models.CreateOrderRequest;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.launchcode.qleanquotes.models.enums.CleaningOption;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute(new CreateQuoteFormDTO());
        model.addAttribute("cleaningOption", CleaningOption.values());
        model.addAttribute("title", "Get Quote");
        return "createquotes";
    }


    @PostMapping("/createquotes")
    public String handleCreateQuoteForm(@ModelAttribute @Valid CreateQuoteFormDTO createQuoteFormDTO,
                                        Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "/createquotes";
        }
                    Quote newQuote = new Quote(createQuoteFormDTO.getSquareFeet(), createQuoteFormDTO.getNumOfRoom(), createQuoteFormDTO.getNumOfBathroom(), createQuoteFormDTO.getCleaningOption());
                    quoteRepository.save(newQuote);
                    setQuoteInsession(request.getSession(), newQuote);
                    model.addAttribute("quote", newQuote);
                    return "createquotes";
                }

}
