package controllers;


import models.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
