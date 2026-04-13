package com.example.whylytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.whylytics.dto.DashboardData;
import com.example.whylytics.service.ProductService;
import com.example.whylytics.service.SaleService;
import com.example.whylytics.utils.Response;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;

    @GetMapping
    public Response<DashboardData> getData(){
        DashboardData dashboardData = new DashboardData();
        dashboardData.setTotalSale(this.saleService.getTotalSaleThisMonth());
        dashboardData.setSaleCount(this.saleService.getSaleCountThisMonth());
        dashboardData.setTopProduct(this.productService.getTopProductThisMonth());
        dashboardData.setGraphData(this.saleService.getGraphData());
        return new Response<DashboardData>(true, "Dashbord data", dashboardData, null);
    }
}
