package com.gouda.notquizlet.service;

import com.gouda.notquizlet.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    void processOAuthPostLogin(String username);
}
