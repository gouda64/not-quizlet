package com.gouda.notquizlet.repository;

import com.gouda.notquizlet.entity.FlashSet;
import com.gouda.notquizlet.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashSetRepository extends JpaRepository<FlashSet,Long> {
    FlashSet findById(long id);
    List<FlashSet> findAllByName(String name);
    void deleteById(long id);
    @Modifying
    @Query("UPDATE FlashSet fs SET fs.flashcards = ?1 WHERE fs.id = ?2")
    void setFlashcardsById(List<Flashcard> flashcards, long id);

    @Modifying
    @Query("UPDATE FlashSet fs SET fs.enabled = ?1 WHERE fs.id = ?2")
    void setEnabledById(boolean enabled, long id);
}
