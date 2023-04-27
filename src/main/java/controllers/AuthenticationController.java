package controllers;


import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthenticationController {


 @Autowired
    CustomerRepository customerRepository;

  private static final String customerSessionKey = "customer";

  public Customer getCustomerFromSession(HttpSession session){
      Integer customerId = (Integer) session.getAttribute(customerSessionKey);
      if(customerId == null){
          return null;
      }
      Optional <Customer> customer = customerRepository.findById(customerId);

      if(customer.isEmpty()){
          return  null;
      }
      return customer.get();
  }
//to be continued

}
