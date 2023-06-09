package com.gouda.notquizlet.service;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface FlashSetService {
    void save(FlashSet flashSet);
    FlashSet findByOwnerAndName(User owner, String name);
    FlashSet findById(long id);
    void deleteById(long id);
    void setFlashcardsById(String[] terms, String[] definitions, long id);
    void setEnabledById(boolean enabled, long id);
}
