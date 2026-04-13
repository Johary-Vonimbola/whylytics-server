package com.example.whylytics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardData {
    private KpiData<Double> totalSale;
    private KpiData<Integer> saleCount;
    private String topProduct;
    private Double[] graphData;

}
