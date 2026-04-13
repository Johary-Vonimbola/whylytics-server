package com.example.whylytics.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.whylytics.dto.KpiData;
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

    public KpiData<Integer> getSaleCountThisMonth(){
        int currentCount = this.saleRepository.countByDateBetween(LocalDateTime.now().withDayOfMonth(1), LocalDateTime.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));
        int previousCount = this.saleRepository.countByDateBetween(LocalDateTime.now().minusMonths(1).withDayOfMonth(1), LocalDateTime.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth()));
        float amount = 0f;
        if(Math.abs(previousCount) > 1e-6){
            amount = (currentCount - previousCount) * 100 / previousCount;
        }
        KpiData<Integer> result = new KpiData<>();
        result.setAmount(currentCount);
        result.setPercent(amount);
        return result;
    }

    public KpiData<Double> getTotalSaleThisMonth(){
        double previousMonth = this.saleRepository.findTotalSaleByMonth(LocalDateTime.now().minusMonths(1));
        double currentMonth = this.saleRepository.findTotalSaleByMonth(LocalDateTime.now());
        float amount = 0f;
        if(Math.abs(previousMonth) > 1e-6){
            amount = (float)((currentMonth - previousMonth) * 100 / previousMonth);
        }
        KpiData<Double> result = new KpiData<>();
        result.setAmount(currentMonth);
        result.setPercent(amount);
        return result;
    }

    public Double[] getGraphData(){
        return this.saleRepository.findMonthlySale();
    }
}
