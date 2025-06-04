package com.example.contactdirectory.repository;

import com.example.contactdirectory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    // Custom query method to find categories by their type (e.g., "Floor", "SubOffice")
    List<Category> findByCategoryType(String categoryType);

    // Example: Find by name if needed
    // List<Category> findByCategoryNameContainingIgnoreCase(String nameFragment);
}
