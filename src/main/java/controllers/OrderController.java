package controllers;


import models.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {




    @Autowired
    private OrderRepository orderRepository;






}
