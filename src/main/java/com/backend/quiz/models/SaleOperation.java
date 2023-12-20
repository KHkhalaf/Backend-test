package com.backend.quiz.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "SaleOperation")
@Data
public class SaleOperation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="creation_date",columnDefinition="TIMESTAMP", nullable = false)
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="seller_id", nullable=false)
    private Seller seller;

    @OneToMany(mappedBy= "saleOperation")
    private Set<Product> products;

    @Column(name = "total")
    private Long total;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Long price;
}
