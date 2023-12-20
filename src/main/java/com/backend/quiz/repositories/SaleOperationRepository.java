package com.backend.quiz.repositories;

import com.backend.quiz.models.SaleOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleOperationRepository extends JpaRepository<SaleOperation, Long> {
}
