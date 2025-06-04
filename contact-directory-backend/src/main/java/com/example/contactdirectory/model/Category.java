package com.example.contactdirectory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_type", nullable = false)
    private String categoryType;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "category") // "category" is the field name in Section entity
    private List<Section> sections;

    // Optional: Custom equals and hashCode if issues arise with Lombok's defaults in collections/JPA contexts
    // For simple entities, Lombok's @Data is usually fine.
    // If sections list causes issues with equals/hashCode (e.g. stack overflow with bidirectional):
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Category category = (Category) o;
    //     return Objects.equals(categoryId, category.categoryId);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(categoryId);
    // }
}
