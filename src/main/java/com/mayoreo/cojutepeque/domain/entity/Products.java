package com.mayoreo.cojutepeque.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private long idproduct;

    @Column(name = "productname")
    private String productname;

    @Column(name = "purchaseprice")
    private double purchaseprice;

    @Column(name = "saleprice")
    private double saleprice;

    @Column(name = "existence")
    private double existence;

    @Column(name = "unitaverage")
    private String unitaverage;

    @Column(name = "iduseradd")
    private long iduseradd;

    @Column(name = "dateadd")
    private String dateadd;

    @Column(name = "idusermod")
    private long idusermod;

    @Column(name = "datemod")
    private String datemod;
}
