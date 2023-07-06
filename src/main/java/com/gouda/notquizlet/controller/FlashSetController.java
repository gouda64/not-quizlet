package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.Flashcard;
import com.gouda.notquizlet.service.FlashSetService;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class FlashSetController {
    private final FlashSetService flashSetService;
    private final UserService userService;

    @Autowired
    public FlashSetController(FlashSetService flashSetService, UserService userService) {
        this.flashSetService = flashSetService;
        this.userService = userService;
    }

    @GetMapping("/new-set")
    public String newSet(Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        return "new-set";
    }

    @PostMapping("/new-set")
    public String makeSet(@RequestParam(value="term") String[] terms,
                          @RequestParam(value="definition") String[] definitions,
                          Principal principal) {
        //TODO: deal with validation
        FlashSet setForm = new FlashSet();
        setForm.setOwner(userService.findByUsername(principal.getName()));
        setForm.setFlashcards(new ArrayList<>());

        for (int i = 0; i < terms.length; i++) {
            Flashcard flashcard = new Flashcard();
            flashcard.setTerm(terms[i]);
            flashcard.setDefinition(definitions[i]);
            setForm.getFlashcards().add(flashcard);
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

        return "set";
    }
}
