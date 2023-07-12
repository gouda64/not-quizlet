package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.Flashcard;
import com.gouda.notquizlet.service.FlashSetService;
import com.gouda.notquizlet.service.UserService;
import com.gouda.notquizlet.validator.FlashSetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class FlashSetController {
    private final FlashSetService flashSetService;
    private final UserService userService;
    private final FlashSetValidator flashSetValidator;

    @Autowired
    public FlashSetController(FlashSetService flashSetService, UserService userService,
                              FlashSetValidator flashSetValidator) {
        this.flashSetService = flashSetService;
        this.userService = userService;
        this.flashSetValidator = flashSetValidator;
    }

    @GetMapping("/new-set")
    public String newSet(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute("setForm", new FlashSet());

        return "set/new-set";
    }

    @PostMapping("/new-set")
    public String makeSet(@RequestParam(value="term") String[] terms,
                          @RequestParam(value="definition") String[] definitions,
                          Principal principal, @ModelAttribute("setForm") FlashSet setForm,
                          BindingResult bindingResult) {
        setForm.setOwner(userService.findByUsername(principal.getName()));
        setForm.setFlashcards(new ArrayList<>());

        for (int i = 0; i < terms.length; i++) {
            if (terms[i].length() == 0 || definitions[i].length() == 0) {
                continue;
            }
            Flashcard flashcard = new Flashcard();
            flashcard.setTerm(terms[i].equals("") ? "..." : terms[i]);
            flashcard.setDefinition(definitions[i].equals("") ? "..." : definitions[i]);
            setForm.getFlashcards().add(flashcard);
        }

        flashSetValidator.validate(setForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "set/new-set";
        }

        setForm.setEnabled(true);

        flashSetService.save(setForm);

        return "redirect:/sets/" + setForm.getId();
    }

    @GetMapping("/sets/{setId}")
    public String studySet(@PathVariable long setId, Principal principal, Model model){
        FlashSet set = flashSetService.findById(setId);

        if (set != null && set.isEnabled()) {
            model.addAttribute("set", set);
        }
        else if (set != null && set.getOwner().getUsername().equals(principal.getName())) {
            return "redirect:/sets/" + setId + "/edit";
        }
        else {
            return "error/404";
        }

        return "set/set";
    }

    @GetMapping("/sets/{setId}/flashcards")
    public String flashcards(@PathVariable long setId, Principal principal, Model model) {
        FlashSet set = flashSetService.findById(setId);

        if (set != null && set.isEnabled()) {
            model.addAttribute("set", set);
        }
        else if (set != null && set.getOwner().getUsername().equals(principal.getName())) {
            return "redirect:/sets/" + setId + "/edit";
        }
        else {
            return "error/404";
        }

        return "set/flashcards";
    }

    @GetMapping("/sets/{setId}/write")
    public String write(@PathVariable long setId, Principal principal, Model model) {
        FlashSet set = flashSetService.findById(setId);

        if (set != null && set.isEnabled()) {
            model.addAttribute("set", set);
        }
        else if (set != null && set.getOwner().getUsername().equals(principal.getName())) {
            return "redirect:/sets/" + setId + "/edit";
        }
        else {
            return "error/404";
        }

        return "set/write";
    }

    @GetMapping("/sets/{setId}/test")
    public String test(@PathVariable long setId, Principal principal, Model model) {
        FlashSet set = flashSetService.findById(setId);

        if (set != null && set.isEnabled()) {
            model.addAttribute("set", set);
        }
        else if (set != null && set.getOwner().getUsername().equals(principal.getName())) {
            return "redirect:/sets/" + setId + "/edit";
        }
        else {
            return "error/404";
        }

        return "set/test";
    }

    @GetMapping("/sets/{setId}/learn")
    public String learn(@PathVariable long setId, Principal principal, Model model) {
        FlashSet set = flashSetService.findById(setId);

        if (set != null && set.isEnabled()) {
            model.addAttribute("set", set);
        }
        else if (set != null && set.getOwner().getUsername().equals(principal.getName())) {
            return "redirect:/sets/" + setId + "/edit";
        }
        else {
            return "error/404";
        }

        return "set/learn";
    }
}
