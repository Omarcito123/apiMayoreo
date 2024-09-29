package com.mayoreo.cojutepeque.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private long idrol;

    @Column(name = "rolname")
    private String rolname;

    @Column(name = "iduseradd")
    private long iduseradd;

    @Column(name = "dateadd")
    private String dateadd;

    @Column(name = "idusermod")
    private long idusermod;

    @Column(name = "datemod")
    private String datemod;
}
