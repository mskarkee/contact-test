package com.example.contactdirectory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @Column(name = "section_id")
    private String sectionId;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @ManyToOne // FetchType.LAZY is often a good default here
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference // To handle potential Jackson serialization loops with Category
    private Category category;

    @OneToMany(mappedBy = "section") // "section" is the field name in Contact entity
    @JsonManagedReference // To handle potential Jackson serialization loops with Contact
    private List<Contact> contacts;

    // Optional: Custom equals and hashCode to avoid issues with bidirectional relationships
    // if Lombok's default @Data causes problems (e.g. with category or contacts fields).
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Section section = (Section) o;
    //     return Objects.equals(sectionId, section.sectionId);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(sectionId);
    // }
}
