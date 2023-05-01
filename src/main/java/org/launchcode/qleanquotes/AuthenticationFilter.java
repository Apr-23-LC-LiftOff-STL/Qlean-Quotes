package org.launchcode.qleanquotes;

import controllers.AuthenticationController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.data.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;




public class AuthenticationFilter implements HandlerInterceptor{


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthenticationController authenticationController;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);


    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {


        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }


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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        Customer customer = authenticationController.getCustomerFromSession(session);

        if(customer != null) {
            modelAndView.addObject("customer" , customer);
        }
    }


   @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                               Object handler,Exception ex) throws Exception {

        String ipAddress = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        int statusCode = response.getStatus();

       logger.info("Request from IP address {}  for URL  {} returned status code {}");
   }



}
