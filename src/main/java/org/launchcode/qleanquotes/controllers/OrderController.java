package org.launchcode.qleanquotes.controllers;


import org.launchcode.qleanquotes.models.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")

@Controller
@RequestMapping("orders")
public class OrderController {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerController customerController;






}
