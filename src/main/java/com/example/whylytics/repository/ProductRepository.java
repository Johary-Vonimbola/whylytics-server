package com.example.whylytics.repository;

import org.springframework.stereotype.Repository;

import com.example.whylytics.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
