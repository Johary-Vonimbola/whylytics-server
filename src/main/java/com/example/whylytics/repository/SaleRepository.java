package com.example.whylytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.whylytics.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer>{
    
}
