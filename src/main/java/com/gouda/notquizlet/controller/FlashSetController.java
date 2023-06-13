package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.service.FlashSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlashSetController {
    private final FlashSetService flashSetService;

    @Autowired
    public FlashSetController(FlashSetService flashSetService) {
        this.flashSetService = flashSetService;
    }

    @GetMapping("/set/new")
    public String newSet(Model model) {
        model.addAttribute("setForm", new FlashSet());
        return "new-set";
    }

    @PostMapping("/new-set")
    public String makeSet(@ModelAttribute("setForm") FlashSet setForm, Model model) {
        //TODO: validation

        flashSetService.save(setForm);

        return "redirect:/set/" + setForm.getId();
    }
}
