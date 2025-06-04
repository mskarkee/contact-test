package com.example.contactdirectory.repository;

import com.example.contactdirectory.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Custom query method to find all contacts within a specific section
    List<Contact> findBySectionSectionId(String sectionId);

    // Custom query method for searching contacts by name (case-insensitive)
    List<Contact> findByNameContainingIgnoreCase(String nameFragment);

    // Custom query method for searching contacts by designation (case-insensitive)
    List<Contact> findByDesignationContainingIgnoreCase(String designationFragment);

    // Example: Find by primary contact number
    // List<Contact> findByContact1(String contact1);
}
