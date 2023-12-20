package com.backend.quiz.services;

import com.backend.quiz.models.Category;
import com.backend.quiz.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    public CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }
}
