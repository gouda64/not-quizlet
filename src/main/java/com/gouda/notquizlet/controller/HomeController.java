package com.gouda.notquizlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "index", "home"})
    public String home(Model model) {
        return "home";
    }
}
