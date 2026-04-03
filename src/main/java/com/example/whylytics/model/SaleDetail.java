package com.example.whylytics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sale_detail")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;
    @Column(name="quantity")
    Integer quantity;
    @Column(name="unit_price")
    Double unitPrice;
    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="sale_id")
    Sale parent;
}
