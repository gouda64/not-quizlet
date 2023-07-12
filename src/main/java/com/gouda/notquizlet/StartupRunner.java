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

        FlashSet flashSet1 = new FlashSet();
        flashSet1.setEnabled(true);
        flashSet1.setName("fish");
        flashSet1.setOwner(user);
        flashSet1.setFlashcards(new ArrayList<>(List.of(new Flashcard(), new Flashcard())));
        flashSet1.getFlashcards().get(0).setTerm("tuna");
        flashSet1.getFlashcards().get(0).setDefinition("high in deliciousness and mercury");
        flashSet1.getFlashcards().get(1).setTerm("sole");
        flashSet1.getFlashcards().get(1).setDefinition("sounds like part of a shoe");
        for (int i = 0; i < 5; i++) {
            Flashcard flashcard = new Flashcard();
            flashcard.setTerm("t" + i);
            flashcard.setDefinition("d" + i);
            flashSet1.getFlashcards().add(flashcard);
        }

        FlashSet flashSet2 = new FlashSet();
        flashSet2.setEnabled(true);
        flashSet2.setName("long fish");
        flashSet2.setOwner(user);
        flashSet2.setFlashcards(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            Flashcard flashcard = new Flashcard();
            flashcard.setTerm("t" + i);
            flashcard.setDefinition("d" + i);
            flashSet2.getFlashcards().add(flashcard);
        }

        flashSetService.save(flashSet1);
        flashSetService.save(flashSet2);
    }
}
