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

import com.example.whylytics.model.Sale;
import com.example.whylytics.model.SaleDetail;
import com.example.whylytics.service.SaleService;
import com.example.whylytics.utils.Response;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<Response<List<Sale>>> getAll(){
        Response<List<Sale>> body = new Response<>(true, "Sale record(s)", this.saleService.getAll(), null);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Response<Sale>> save(@RequestBody Sale sale){
        if(sale.getSaleDetails() != null){
            for(SaleDetail saleDetail : sale.getSaleDetails()){
                sale.addDetail(saleDetail);
            }
        }
        Response<Sale> body = new Response<>(true, "Sale saved", this.saleService.save(sale), null);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Integer id){
        this.saleService.delete(id);
        Response<Void> body = new Response<>(true, "Sale deleted", null, null);
        return ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<Sale>> getById(@PathVariable Integer id)throws Exception{
        Response<Sale> body = new Response<>(true, "Sale saved", this.saleService.getById(id), null);
        return ResponseEntity.ok(body);
    }

    


}
