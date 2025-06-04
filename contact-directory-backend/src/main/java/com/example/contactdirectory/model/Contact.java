package com.example.contactdirectory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_pk")
    private Integer contactPk;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "call_sign")
    private String callSign;

    @Column(name = "landline")
    private String landline;

    @Column(name = "contact1", nullable = false)
    private String contact1; // Official contact

    @Column(name = "contact2")
    private String contact2; // Personal contact

    @Column(name = "email")
    private String email;

    @Lob // For potentially longer text, TEXT type in SQL
    @Column(name = "details")
    private String details;

    @ManyToOne // FetchType.LAZY is often a good default here
    @JoinColumn(name = "section_id", nullable = false)
    @JsonBackReference // To handle potential Jackson serialization loops with Section
    private Section section;

    // Optional: Custom equals and hashCode
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Contact contact = (Contact) o;
    //     return Objects.equals(contactPk, contact.contactPk);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(contactPk);
    // }
}
