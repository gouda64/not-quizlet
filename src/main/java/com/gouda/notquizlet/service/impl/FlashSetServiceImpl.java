package com.gouda.notquizlet.service.impl;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.repository.FlashSetRepository;
import com.gouda.notquizlet.service.FlashSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashSetServiceImpl implements FlashSetService {
    private final FlashSetRepository flashSetRepository;

    @Autowired
    public FlashSetServiceImpl(FlashSetRepository flashSetRepository) {
        this.flashSetRepository = flashSetRepository;
    }

    @Override
    public void save(FlashSet flashSet) {
        flashSetRepository.save(flashSet);
    }

    @Override
    public FlashSet findByOwnerAndName(User owner, String name) {
        for (FlashSet fs : owner.getSets()) {
            if (fs.getName().equals(name)) {
                return fs;
            }
        }
        return null;
    }

    @Override
    public FlashSet findById(long id) {
        return flashSetRepository.findById(id);
    }
}
