package com.backend.quiz.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(length = 50, name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(length = 50, name = "last_name", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy="client")
    private Set<SaleOperation> saleOperations;

}
