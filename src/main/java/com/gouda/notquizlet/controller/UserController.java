package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.gouda.notquizlet.validator.SignUpValidator;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final SignUpValidator signUpValidator;

    @Autowired
    public UserController(UserService userService, SignUpValidator signUpValidator) {
        this.userService = userService;
        this.signUpValidator = signUpValidator;
    }

    @GetMapping("/{username}")
    public String userPanel(@PathVariable String username, Model model){
        User user = userService.findByUsername(username);

        if (user != null) {
            model.addAttribute("user", user);
        }
        else {
            return "error/404";
        }

        return "user";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("signupForm", new User());

        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signupForm") User userForm, BindingResult bindingResult) {
        signUpValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.save(userForm);
        userService.login(userForm.getUsername(), userForm.getPassword());
        //TODO: fix login page redirect + doesn't log in?

        return "redirect:/";
    }

    @GetMapping("/login")
    public String logIn(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/";
        }

        model.addAttribute("loginForm", new User());

        //TODO: error
        return "login";
    }

    @PostMapping("/login")
    public String logIn(@ModelAttribute("loginForm") User loginForm, Model model) {
        userService.login(loginForm.getUsername(), loginForm.getPassword());

        return "redirect:/";
    }
}
