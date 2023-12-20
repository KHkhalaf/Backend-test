package com.backend.quiz.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "seller")
@Data
public class Seller {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, name = "full_name")
    @NotNull
    private String fullName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @OneToMany(mappedBy="seller")
    private Set<SaleOperation> saleOperations;
}
