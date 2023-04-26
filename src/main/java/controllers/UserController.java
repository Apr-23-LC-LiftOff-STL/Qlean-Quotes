package controllers;



import models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/* NOTE1: The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation.
 Response from a web application is generally view (HTML + CSS + JavaScript) */

/* NOTE2 :@CrossOrigin(origins = "http://localhost:4200") is a Java annotation that can be used in a Spring Boot application to allow
cross-origin requests from a specified origin.*/

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("")
//    public String index(Model model){
//
//
//        return "redirect";
//    }

//
//
//    @PostMapping("add")
//    void addUser(@RequestBody)



}
