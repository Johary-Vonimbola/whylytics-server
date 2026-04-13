package com.example.whylytics.repository;

import org.springframework.stereotype.Repository;

import com.example.whylytics.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(value = "SELECT \n" + 
                "    name\n" + 
                "FROM \n" + 
                "    v_product_sale_per_day\n" + 
                "WHERE TO_CHAR(date, 'YYYY-MM')=TO_CHAR(NOW(), 'YYYY-MM')\n" + 
                "GROUP BY\n" + 
                "    name\n" + 
                "ORDER BY SUM(total_sale) DESC LIMIT 1", nativeQuery = true)
    public String findTopProductThisMonth();

}
