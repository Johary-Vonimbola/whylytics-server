package com.example.whylytics.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.whylytics.model.Sale;
import com.example.whylytics.repository.SaleRepository;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    //lazy load
    public List<Sale> getAll(){
        return this.saleRepository.findAll();
    }

    public Sale getById(Integer id) throws Exception {
        return this.saleRepository.findById(id).orElseThrow(() -> new Exception("Sale not found"));
    }

    public Sale save(Sale sale){
        return this.saleRepository.save(sale);
    }

    public void delete(Integer id){
        if(!this.saleRepository.existsById(id)){
            throw new RuntimeException("Sale with id "+id+" doesn't exist");
        }
        this.saleRepository.deleteById(id);
    }
}
