package com.example.whylytics.model;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sale")
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;
    @Column(name="date")
    Date date;
    @Column(name="total")
    Double total;

    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    Set<SaleDetail> saleDetails;

    public void removeDetail(SaleDetail detail){
        this.saleDetails.remove(detail);
        detail.setParent(null);
    }

    public void addDetail(SaleDetail detail){
        this.saleDetails.add(detail);
        detail.setParent(this);
    }
}
