package org.launchcode.qleanquotes.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.QuoteCalculator;
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

        QuoteCalculator quoteCalculator = new QuoteCalculator();
        Quote quote = new Quote();
        quote.setSquareFeet(createQuoteFormDTO.getSquareFeet());
        quote.setNumOfRoom(createQuoteFormDTO.getNumOfRoom());
        quote.setNumOfBathroom(createQuoteFormDTO.getNumOfBathroom());
        quote.setCleaningOption(createQuoteFormDTO.getCleaningOption());
        Long calculatedTotalCharge = quoteCalculator.calculateTotalCharge(quote);
        double calculateTotalCost = quoteCalculator.calculateTotalCost(quote);
        quote.setTotalCharge(calculatedTotalCharge);
        quote.setTotalCost(calculateTotalCost);
                setQuoteInsession(request.getSession(), quote);
                model.addAttribute("quote", quote);
                quoteRepository.save(quote);
                    return "createquotes";
                }

}
