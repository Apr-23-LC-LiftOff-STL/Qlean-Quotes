package org.launchcode.qleanquotes.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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



        double totalCost = 0.0;
        if (createQuoteFormDTO.getSquareFeet() != null && createQuoteFormDTO.getNumOfRoom() != null) {
            totalCost += createQuoteFormDTO.getSquareFeet() * 0.5 + createQuoteFormDTO.getNumOfRoom() * 20;

            if (createQuoteFormDTO.getNumOfBathroom() != null) {
                totalCost += createQuoteFormDTO.getNumOfBathroom() * 30;
            }

            model.addAttribute("totalCost", totalCost);
            return "createquotes";
        }

       // esra/createquoteandpaymentpage
        Quote newQuote = new Quote(createQuoteFormDTO.getSquareFeet(), createQuoteFormDTO.getNumOfRoom(), createQuoteFormDTO.getNumOfBathroom());
        quoteRepository.save(newQuote);
        setQuoteInsession(request.getSession(), newQuote);
        model.addAttribute("quote", newQuote);

        return "redirect:/payment";

    }

}
