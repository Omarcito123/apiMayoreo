package com.mayoreo.cojutepeque.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private long iduser;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "secondname")
    private String secondname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "secondsurname")
    private String secondsurname;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "dateborn")
    private String dateborn;

    @Column(name = "iduseradd")
    private long iduseradd;

    @Column(name = "dateadd")
    private String dateadd;

    @Column(name = "idusermod")
    private long idusermod;

    @Column(name = "datemod")
    private String datemod;

    @Column(name = "idrol")
    private long idrol;

    @Column(name = "rolname")
    private String rolname;
}
