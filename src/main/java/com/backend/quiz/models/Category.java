package com.backend.quiz.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, name = "name")
    @NotNull
    private String name;

    @Column(length = 50, name = "description")
    @NotNull
    private String description;

    @OneToMany(mappedBy="category") 
    private Set<Product> products;
}
