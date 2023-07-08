package com.gouda.notquizlet;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public StartupRunner(UserService userService) {
        this.userService = userService;
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
    }
}
