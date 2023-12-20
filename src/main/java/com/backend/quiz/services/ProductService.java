package com.backend.quiz.services;

import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.Client;
import com.backend.quiz.models.Product;
import com.backend.quiz.repositories.CategoryRepository;
import com.backend.quiz.repositories.ClientRepository;
import com.backend.quiz.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    public ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public void save(Product product) {
        product.setCreationDate(LocalDate.now());
        productRepository.save(product);
    }

    public void editProduct(Product product) throws ResourceNotFoundException {
        Product _product = get(product.getId());
        _product.setName(product.getName());
        _product.setDescription(product.getDescription());
        _product.setCategory(product.getCategory());

        productRepository.save(_product);
    }
    public Product get(long id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
    }
}
