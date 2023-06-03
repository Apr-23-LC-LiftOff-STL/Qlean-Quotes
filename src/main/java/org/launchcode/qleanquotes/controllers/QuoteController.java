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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
//@RequestMapping("orders")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    private static final String quoteSessionKey = "quote";

    public static void setQuoteInsession(HttpSession session, Quote quote) {
        session.setAttribute(quoteSessionKey, quote.getId());
    }

    public static Integer getQuoteIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute(quoteSessionKey);
    }

    @GetMapping("/createquote")
    public String showCreateQuoteForm(Model model){
        model.addAttribute(new CreateQuoteFormDTO());
        model.addAttribute("cleaningOption", CleaningOption.values());
        model.addAttribute("title", "Get Quote");
        return "createquote";
    }


    @PostMapping("/createquote")
    public String handleCreateQuoteForm(@ModelAttribute @Valid CreateQuoteFormDTO createQuoteFormDTO,
                                        Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "createquote";
        }

        QuoteCalculator quoteCalculator = new QuoteCalculator();
        Quote quote = new Quote();
        quote.setSquareFeet(createQuoteFormDTO.getSquareFeet());
        quote.setNumOfRoom(createQuoteFormDTO.getNumOfRoom());
        quote.setNumOfBathroom(createQuoteFormDTO.getNumOfBathroom());
        quote.setCleaningOption(createQuoteFormDTO.getCleaningOption());
        Long calculatedTotalCharge = quoteCalculator.calculateTotalCharge(quote);
        double calculatedTotalCost = quoteCalculator.calculateTotalCost(quote);
        String formattedTotalCost = quoteCalculator.formatTotalCost(quote);
        quote.setTotalCharge(calculatedTotalCharge);
        quote.setTotalCost(calculatedTotalCost);
        quote.setFormattedTotalCost(formattedTotalCost);
        setQuoteInsession(request.getSession(), quote);
        quoteRepository.save(quote);
        model.addAttribute("quote", quote);
        return "createquote";
    }


    @GetMapping("createquote/{quoteId}")
    public String displayNewQuote(Model model, @PathVariable int quoteId, HttpServletRequest request, Errors errors) {

        HttpSession session = request.getSession();
        Integer storedQuoteId = getQuoteIdFromSession(session);
        Optional<Quote> optionalQuote = quoteRepository.findById(quoteId);

        if (storedQuoteId != null && storedQuoteId == quoteId) {
            Quote quote = optionalQuote.get();
            model.addAttribute("quote", quote);
        } else {
            model.addAttribute("errors", errors);
            return "createquote";
        }
        return "createquote";
    }

}
