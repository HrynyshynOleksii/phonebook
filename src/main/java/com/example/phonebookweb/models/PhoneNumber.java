package com.example.phonebookweb.models;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter


@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private long id;
    private String fullName;
    private Long number;
    private String email;

    private LocalDateTime time;

    public PhoneNumber(int id) {
        this.id = id;
    }

    public PhoneNumber(String fullName, Long number, String email) {
        this.fullName = fullName;
        this.number = number;
        this.email = email;
        this.time = LocalDateTime.now();
    }

    public PhoneNumber() {
    }
}

