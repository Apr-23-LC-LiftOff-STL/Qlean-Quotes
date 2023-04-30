package org.launchcode.qleanquotes;

import controllers.AuthenticationController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.servlet.HandlerInterceptor;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;



public class AuthenticationFilter implements HandlerInterceptor {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthenticationController authenticationController;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {


        HttpSession session = request.getSession();
        Customer customer = authenticationController.getCustomerFromSession(session);

        //The customer is logged in
        if(customer != null) {
            return true;
        }
        //The customer is NOT logged in
        response.sendRedirect("/login");
        return false;
    }


}
