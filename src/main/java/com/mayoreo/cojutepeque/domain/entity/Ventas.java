package com.mayoreo.cojutepeque.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ventas")
@Getter
@Setter
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private long idventa;

    @Column(name = "bill")
    private String bill;

    @Column(name = "idproducto")
    private long idproducto;

    @Column(name = "productname")
    private String productname;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "existence")
    private double existence;

    @Column(name = "purchaseunitprice")
    private double purchaseunitprice;

    @Column(name = "totalpurchaseprice")
    private double totalpurchaseprice;

    @Column(name = "totalsaleprice")
    private double totalsaleprice;

    @Column(name = "totalunitsaleprice")
    private double totalunitsaleprice;

    @Column(name = "ganancia")
    private double ganancia;

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
