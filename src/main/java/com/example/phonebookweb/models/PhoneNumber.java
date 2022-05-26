package com.example.phonebookweb.models;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter


@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private int id;
    private String fullName;
    private Long number;
    private String email;

    public PhoneNumber(int id) {
        this.id = id;
    }

    public PhoneNumber(int id, String fullName, Long number, String email) {
        this.id = id;
        this.fullName = fullName;
        this.number = number;
        this.email = email;
    }

    public PhoneNumber() {
    }
}

