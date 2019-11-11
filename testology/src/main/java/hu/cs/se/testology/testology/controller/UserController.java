package hu.cs.se.testology.testology.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hu.cs.se.testology.testology.model.User;
import hu.cs.se.testology.testology.services.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

@Controller
public class UserController {

    private boolean isRoleNotSelected = false;
    private boolean isPasswordAndConfirmPasswordMisMatchError = false;
    private boolean isUsernameDuplicate = false;

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String renderUserLoginPage(){


        return "user/login";
    }

    @GetMapping("/user/register")
    public String renderSignUpPage(Model model){

        model.addAttribute("user" , new User());
        model.addAttribute("isMismatchPassword" , isPasswordAndConfirmPasswordMisMatchError);
        model.addAttribute("isNoRole" , isRoleNotSelected);
        model.addAttribute("isUsernameDuplicate" , isUsernameDuplicate);

        return "user/register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user, @RequestParam String password , @RequestParam String confirm, Model model){

        boolean isDataValid = true;

        if(!password.equals(confirm)){
            isPasswordAndConfirmPasswordMisMatchError = true;
            isDataValid = false;
        }

        if(user.getRole().equals("NO_ROLE")){
            isRoleNotSelected = true;
            isDataValid = false;
        }

        if(userService.findByUsername(user.getUsername()) != null){
            isUsernameDuplicate = true;
            isDataValid = false;
        }

        if(!isDataValid){
            System.out.println(user.getUsername());
            model.addAttribute("user", user);
            return "redirect:/user/register";
        }

        userService.save(user);

        return "redirect:/user/login";
    }

}
