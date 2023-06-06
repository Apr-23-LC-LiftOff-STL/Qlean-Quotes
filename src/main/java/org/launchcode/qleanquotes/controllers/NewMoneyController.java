package org.launchcode.qleanquotes.controllers;

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Address;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.Money;
import jakarta.servlet.http.HttpSession;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;
import org.launchcode.qleanquotes.wrappers.SquareWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.launchcode.qleanquotes.controllers.QuoteController.quoteSessionKey;

@Controller
public class NewMoneyController {

    private final SquareWrapper squareWrapper;

    @Autowired
    public NewMoneyController(SquareWrapper squareWrapper) {
        this.squareWrapper = squareWrapper;
    }

    @GetMapping("/payment")
    public String showPaymentForm(HttpSession session, Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        Quote quote = (Quote) session.getAttribute(quoteSessionKey);
        if (quote != null) {
            model.addAttribute("quote", quote);
        }
        model.addAttribute("paymentFormDTO", new PaymentFormDTO());
        return "payment";
    }

    @RequestMapping("")
    public String index(Map<String, Object> model) throws InterruptedException, ExecutionException {
        model.put("paymentFormUrl",
                squareWrapper.getSquareEnvironment());
        model.put("locationId", squareWrapper.getSquareLocationId());
        model.put("appId", squareWrapper.getSquareAppId());
        model.put("idempotencyKey", UUID.randomUUID().toString());

        return "payment";
    }

    @PostMapping("/payment")
    public String createPaymentRequest(@ModelAttribute PaymentFormDTO paymentFormDTO,
                                       HttpSession session, Model model) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customer", customer);
        Quote quote = (Quote) session.getAttribute(quoteSessionKey);
        if (quote != null) {
            model.addAttribute("quote", quote);
        }
        try {
            Money amountMoney = new Money.Builder()
                    .amount(quote.getTotalCharge())
                    .currency("USD")
                    .build();

            Address billingAddress = new Address.Builder()
//                    .firstName(customer.getName())
//                    .lastName(customer.getLastName())
                    .addressLine1(paymentFormDTO.getBillingAddressLine1())
                    .addressLine2(paymentFormDTO.getBillingAddressLine2())
                    .locality(paymentFormDTO.getBillingLocality())
                    .administrativeDistrictLevel1(paymentFormDTO.getBillingAdministrativeDistrictLevel1())
                    .postalCode(paymentFormDTO.getBillingPostalCode())
                    .build();

            Address shippingAddress = new Address.Builder()
//                    .firstName(customer.getName())
//                    .lastName(customer.getLastName())
                    .addressLine1(paymentFormDTO.getShippingAddressLine1())
                    .addressLine2(paymentFormDTO.getShippingAddressLine2())
                    .locality(paymentFormDTO.getShippingLocality())
                    .administrativeDistrictLevel1(paymentFormDTO.getShippingAdministrativeDistrictLevel1())
                    .postalCode(paymentFormDTO.getShippingPostalCode())
                    .build();

            CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest
                    .Builder(paymentFormDTO.getToken(), paymentFormDTO.getIdempotencyKey())
                    .amountMoney(amountMoney)
//                    .buyerEmailAddress(customer.getEmail())
//                    .note(customer.getPhoneNumber())
                    .billingAddress(billingAddress)
                    .shippingAddress(shippingAddress)
                    .build();

            PaymentResult paymentResult = squareWrapper.createPayment(createPaymentRequest);
            System.out.println(paymentResult.getTitle());
            model.addAttribute("paymentResult", paymentResult);
            if (paymentResult.getTitle().equals("SUCCESS")) {
                System.out.println("Payment Successful");
            } else {
                System.out.println("Payment Failed");
            }
        } catch (ApiException | IOException e) {
            System.out.println(e);
        }
        return "payment-successful";
    }
}
