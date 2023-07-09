package com.gouda.notquizlet;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.Flashcard;
import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.FlashSetService;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {
    private final UserService userService;
    private final FlashSetService flashSetService;

    @Autowired
    public StartupRunner(UserService userService, FlashSetService flashSetService) {
        this.userService = userService;
        this.flashSetService = flashSetService;
    }


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("foo");
        user.setPassword("pass");
        user.setEmail("foo@example.com");
        user.setEnabled(true);
//        user.setProvider(Provider.LOCAL);
        userService.save(user);

        FlashSet flashSet = new FlashSet();
        flashSet.setEnabled(true);
        flashSet.setName("fish");
        flashSet.setOwner(user);
        flashSet.setFlashcards(new ArrayList<>(List.of(new Flashcard(), new Flashcard())));
        flashSet.getFlashcards().get(0).setTerm("tuna");
        flashSet.getFlashcards().get(0).setDefinition("high in deliciousness and mercury");
        flashSet.getFlashcards().get(1).setTerm("sole");
        flashSet.getFlashcards().get(1).setDefinition("sounds like part of a shoe");

        flashSetService.save(flashSet);
    }
}
