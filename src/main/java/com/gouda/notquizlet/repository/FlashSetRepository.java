package com.gouda.notquizlet.repository;

import com.gouda.notquizlet.entity.FlashSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashSetRepository extends JpaRepository<FlashSet,Long> {
    FlashSet findById(long id);
    List<FlashSet> findAllByName(String name);
}
