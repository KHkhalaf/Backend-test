package com.backend.quiz.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(length = 50, name = "description", nullable = false)
    private String description;

    @Column(name="creation_date",columnDefinition="TIMESTAMP", nullable = false)
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="sale_operation_id")
    private SaleOperation saleOperation;

}
