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
        model.addAttribute("title", "Get Quote");
        return "createquotes";
    }


    @PostMapping("/createquotes")
    public String handleCreateQuoteForm(@ModelAttribute @Valid CreateQuoteFormDTO createQuoteFormDTO,
                                        Errors errors, HttpServletRequest request, Model model) {

//        //TODO align API requirements and desired functionality with data to be collected client-side
//        //TODO replace hard-coded strings with Customer variables
//            OrderLineItemModifier orderLineItemModifier = new OrderLineItemModifier.Builder()
//                    .catalogObjectId("{MODIFIER_ID}")
//                    .quantity("1")
//                    .build();
//
//            LinkedList<OrderLineItemModifier> modifiers = new LinkedList<>();
//            modifiers.add(orderLineItemModifier);
//
//            OrderLineItem orderLineItem = new OrderLineItem.Builder("1")
//                    .catalogObjectId("{ITEM_VARIATION_ID}")
//                    .modifiers(modifiers)
//                    .build();
//
//            LinkedList<OrderLineItem> lineItems = new LinkedList<>();
//            lineItems.add(orderLineItem);
//
//            Order order = new Order.Builder("{LOCATION_ID}")
//                    .lineItems(lineItems)
//                    .build();
//
//            CreateOrderRequest request = new CreateOrderRequest.Builder()
//                    .order(order)
//                    .idempotencyKey("{UNIQUE_KEY}")
//                    .build();
//
//            ordersApi.createOrderAsync(request)
//                    .thenAccept(result -> {
//                        System.out.println("Success!");
//                    })
//                    .exceptionally(exception -> {
//                        System.out.println("Failed to make the request");
//                        System.out.println(String.format("Exception: %s", exception.getMessage()));
//                        return null;
//                    });
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


            if (createQuoteFormDTO.getCleaningOptions() != null) {
                if (createQuoteFormDTO.getCleaningOptions().equals("deep")) {
                    totalCost += 50.0;
                } else if (createQuoteFormDTO.getCleaningOptions().equals("average")) {
                    totalCost += 25.0;
                }
            }


            model.addAttribute("totalCost", totalCost);
            return "createquotes";
        }


       // esra/createquoteandpaymentpage
        Quote newQuote = new Quote(createQuoteFormDTO.getSquareFeet(), createQuoteFormDTO.getNumOfRoom(), createQuoteFormDTO.getNumOfBathroom(), createQuoteFormDTO.getCleaningOptions());
        quoteRepository.save(newQuote);
        setQuoteInsession(request.getSession(), newQuote);
        model.addAttribute("quote", newQuote);

        return "redirect:/payment";

    }

}
