package com.example.whylytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.whylytics.model.Product;
import com.example.whylytics.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product getById(Integer id) throws Exception{
        return this.productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    public Product save(Product product){
        return this.productRepository.save(product);
    }
    
    public void delete(Integer id) throws Exception{
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public String getTopProductThisMonth(){
        return this.productRepository.findTopProductThisMonth();
    }
}
