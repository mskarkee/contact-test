package com.example.contactdirectory.controller;

import com.example.contactdirectory.model.Contact;
import com.example.contactdirectory.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<Contact>> getContactsBySectionId(@PathVariable String sectionId) {
        List<Contact> contacts = contactRepository.findBySectionSectionId(sectionId);
        if (contacts.isEmpty()) {
            // It's debatable whether to return 404 or an empty list with 200 OK.
            // If sectionId is valid but has no contacts, 200 OK with empty list is common.
            // For now, sticking to "return 404 if entities not found" broadly.
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchContacts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String term // General term, not yet fully implemented with complex logic
    ) {
        List<Contact> contacts = new ArrayList<>();
        boolean searched = false;

        if (name != null && !name.isEmpty()) {
            contacts.addAll(contactRepository.findByNameContainingIgnoreCase(name));
            searched = true;
        }

        // If name search yielded results or if designation is the primary search field
        if (designation != null && !designation.isEmpty()) {
            if (searched && !contacts.isEmpty()) {
                // If already searched by name and found results, we might skip designation
                // or combine results. For simplicity, let's say name takes precedence if it yields results.
                // Or, if we want to add to the list:
                // contacts.addAll(contactRepository.findByDesignationContainingIgnoreCase(designation));
            } else if (!searched) { // Only search by designation if not searched by name yet
                contacts.addAll(contactRepository.findByDesignationContainingIgnoreCase(designation));
                searched = true;
            }
        }

        // Basic handling for 'term' - can be expanded
        // This is a simplified 'term' search. A real implementation might query multiple fields
        // or use a full-text search index.
        if (term != null && !term.isEmpty() && !searched) { // Only if no specific field search was effective
            contacts.addAll(contactRepository.findByNameContainingIgnoreCase(term));
            if (contacts.isEmpty()) { // If name search by term yields nothing, try designation
                contacts.addAll(contactRepository.findByDesignationContainingIgnoreCase(term));
            }
            // Could also search by callSign, email, etc. and combine results.
            // Example: contacts.addAll(contactRepository.findByCallSignContainingIgnoreCase(term));
            searched = true;
        }

        if (!searched) { // No search parameters provided
            return ResponseEntity.badRequest().body(null); // Or return all contacts, depending on desired behavior
        }

        if (contacts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contacts);
    }
}
