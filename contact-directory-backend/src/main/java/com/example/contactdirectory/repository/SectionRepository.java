package com.example.contactdirectory.repository;

import com.example.contactdirectory.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, String> {

    // Custom query method to find all sections belonging to a specific category
    List<Section> findByCategoryCategoryId(String categoryId);

    // Example: Find sections by name
    // List<Section> findBySectionNameContainingIgnoreCase(String nameFragment);
}
