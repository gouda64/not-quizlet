package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.service.FlashSetService;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class FlashSetController {
    private final FlashSetService flashSetService;
    private final UserService userService;

    @Autowired
    public FlashSetController(FlashSetService flashSetService, UserService userService) {
        this.flashSetService = flashSetService;
        this.userService = userService;
    }

    @GetMapping("/sets/new")
    public String newSet(Principal principal, Model model) {
        model.addAttribute("setForm", new FlashSet());
        FlashSet flashSet = new FlashSet();
        flashSet.setOwner(userService.findByUsername(principal.getName())); //better way?
        flashSetService.save(flashSet);

        return "redirect:/";
    }

    @PostMapping("/sets/new")
    public String makeSet(@ModelAttribute("setForm") FlashSet setForm, Model model) {
        //TODO: validation

        flashSetService.save(setForm);

        return "redirect:/set/" + setForm.getId();
    }
}
