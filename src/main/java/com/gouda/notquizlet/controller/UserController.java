package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import com.gouda.notquizlet.validator.SignUpValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final SignUpValidator signUpValidator;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, SignUpValidator signUpValidator,
                          UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.signUpValidator = signUpValidator;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/{username}")
    public String userPanel(@PathVariable String username, Model model){
        User user = userService.findByUsername(username);

        if (user != null && user.isEnabled()) {
            model.addAttribute("user", user);
        }
        else {
            return "error/404";
        }

        return "user";
    }

    @GetMapping("/signup")
    public String signUp(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/";
        }

        model.addAttribute("signupForm", new User());

        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signupForm") User userForm, BindingResult bindingResult,
                         HttpServletRequest request) {
        signUpValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.save(userForm);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userForm.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String logIn(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/";
        }

        model.addAttribute("loginForm", new User());

        return "login";
    }

    @PostMapping("/login")
    public String logIn(@ModelAttribute("loginForm") User loginForm, Model model) {
        userService.login(loginForm.getUsername(), loginForm.getPassword());

        return "redirect:/";
    }
}
