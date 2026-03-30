package com.example.whylytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.whylytics.model.Product;
import com.example.whylytics.service.ProductService;
import com.example.whylytics.utils.Response;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Response<List<Product>> getAll(){
        List<Product> products = this.productService.getAll();
        return new Response<List<Product>>(true, "Product record(s)", products, null);
    }

}
