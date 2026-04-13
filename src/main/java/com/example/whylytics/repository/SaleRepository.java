package com.example.whylytics.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.whylytics.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer>{

    @Query(value = "SELECT COALESCE(SUM(total_sale), 0)\n" +
                "    FROM v_product_sale_per_day\n" +
                "    WHERE TO_CHAR(date, 'YYYY-MM') = TO_CHAR(CAST(:date AS DATE), 'YYYY-MM')"
                , nativeQuery = true)
    public Double findTotalSaleByMonth(@Param(value = "date")  LocalDateTime date);

    @Query(value = "SELECT \n" + 
                "    name\n" + 
                "FROM \n" + 
                "    v_product_sale_per_day\n" + 
                "WHERE TO_CHAR(date, 'YYYY-MM')=TO_CHAR(NOW(), 'YYYY-MM')\n" + 
                "GROUP BY\n" + 
                "    name\n" + 
                "ORDER BY SUM(total_sale) DESC LIMIT 1", nativeQuery = true)
    public String findTopProductThisMonth();

    public int countByDateBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "select coalesce(sum(total), 0) from sale where extract(year from date)=extract(year from now()) group by to_char(date, 'YYYY-MM')\n", nativeQuery = true)
    public Double[] findMonthlySale();

}
