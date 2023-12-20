package com.backend.quiz.services;

import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.SaleOperation;
import com.backend.quiz.repositories.SaleOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SaleOperationService {

    public SaleOperationRepository saleOperationRepository;
 

    public SaleOperationService(SaleOperationRepository saleOperationRepository) {
        this.saleOperationRepository = saleOperationRepository; 
    }

    public List<SaleOperation> listAll(){
        return saleOperationRepository.findAll();
    }

    @Transactional // override the readOnly attribute , which is readOnly=false
    public void save(SaleOperation saleOperation) {
        saleOperation.setCreationDate(LocalDate.now());
        saleOperationRepository.save(saleOperation);
    }

    @Transactional // override the readOnly attribute , which is readOnly=false
    public void editSaleOperation(long id, long quantity, long price) throws ResourceNotFoundException {
 
        SaleOperation _saleOperation = get(id);
        _saleOperation.setQuantity(quantity);
        _saleOperation.setPrice(price);

        saleOperationRepository.save(_saleOperation);
    }

    public SaleOperation get(long id) throws ResourceNotFoundException {
        return saleOperationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale Operation not found for this id :: " + id));
    }
}
