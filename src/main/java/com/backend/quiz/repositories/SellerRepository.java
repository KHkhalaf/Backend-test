package com.backend.quiz.repositories;

import com.backend.quiz.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
