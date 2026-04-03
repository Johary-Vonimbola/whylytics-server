package com.example.whylytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Response<List<Product>>> getAll(){
        List<Product> products = this.productService.getAll();
        Response<List<Product>> body = new Response<>(true, "Product record(s)", products, null); 
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Response<Product>> save(@RequestBody Product product){
        Response<Product> body = new Response<>(true, "Product saved", this.productService.save(product), null);
        return ResponseEntity.ok(body);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Integer id) throws Exception{
        this.productService.delete(id);
        Response<Void> body = new Response<>(true, "Product deleted", null, null);
        return ResponseEntity.ok(body);
    }
}
