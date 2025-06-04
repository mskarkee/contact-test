package com.example.contactdirectory.controller;

import com.example.contactdirectory.model.Section;
import com.example.contactdirectory.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable String id) {
        Optional<Section> section = sectionRepository.findById(id);
        return section.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Section>> getSectionsByCategoryId(@PathVariable String categoryId) {
        List<Section> sections = sectionRepository.findByCategoryCategoryId(categoryId);
        if (sections.isEmpty()) {
            return ResponseEntity.notFound().build(); // Or ResponseEntity.ok with empty list
        }
        return ResponseEntity.ok(sections);
    }
}
