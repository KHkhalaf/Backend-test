package com.backend.quiz.services;

import com.backend.quiz.models.Seller;
import com.backend.quiz.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    public SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> listAll(){
        return sellerRepository.findAll();
    }
}
