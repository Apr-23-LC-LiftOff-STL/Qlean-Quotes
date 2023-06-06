package org.launchcode.qleanquotes.controllers;

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Address;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.Money;
import org.springframework.web.util.ContentCachingRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.QuoteCalculator;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;
import org.launchcode.qleanquotes.models.enums.CleaningOption;
import org.launchcode.qleanquotes.wrappers.CachedBodyHttpServletRequest;
import org.launchcode.qleanquotes.wrappers.SquareWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
//@RequestMapping("orders")
public class QuoteController {

    private final SquareWrapper squareWrapper;

    @Autowired
    public QuoteController(SquareWrapper squareWrapper) {
        this.squareWrapper = squareWrapper;
    }

    @Autowired
    private QuoteRepository quoteRepository;

    public static final String quoteSessionKey = "quote";


    public static void setQuoteInsession(HttpSession session, Quote quote) {
        session.setAttribute(quoteSessionKey, quote);
    }


    @GetMapping("/createquote")
    public String showCreateQuoteForm(Model model){
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
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
        if (createQuoteFormDTO.getNumOfBathroom() == null) {
            quote.setNumOfBathroom(0);
        } else if (createQuoteFormDTO.getNumOfBathroom() != null) {
            quote.setNumOfBathroom(createQuoteFormDTO.getNumOfBathroom());
        }
        quote.setCleaningOption(createQuoteFormDTO.getCleaningOption());
        Long calculatedTotalCharge = quoteCalculator.calculateTotalCharge(quote);
        double calculatedTotalCost = quoteCalculator.calculateTotalCost(quote);
        String formattedTotalCost = quoteCalculator.formatTotalCost(quote);
        quote.setTotalCharge(calculatedTotalCharge);
        quote.setTotalCost(calculatedTotalCost);
        quote.setFormattedTotalCost(formattedTotalCost);
        setQuoteInsession(request.getSession(), quote);
        quoteRepository.save(quote);
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        model.addAttribute("quote", quote);
        return "createquote";
    }


    @GetMapping("createquote/{quoteId}")
    public String displayNewQuote(Model model, @PathVariable int quoteId, HttpSession session, Errors errors) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        Quote quote = (Quote) session.getAttribute(quoteSessionKey);
        if (quote != null && quote.getId() == quoteId) {
            model.addAttribute("quote", quote);
        } else {
            model.addAttribute("errors", errors);
            return "createquote";
        }
        return "createquote";
    }

}

