package hu.cs.se.testology.testology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/register")
    public String renderUserSignUpPage(){

        return ("/register");
    }

    @PostMapping("/user/create")
    public String createUser(){


        return ("redirect:/user/login");
    }


    @GetMapping("/user/login")
    public String renderUserLoginPage(){

        return "/login";
    }

}
