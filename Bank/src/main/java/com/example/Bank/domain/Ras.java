package com.example.Bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Ras {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID idOfCr;
    private String date;
    private Double summa, summaTelCr, summaPr;

    public Ras() {
    }

    public Ras(UUID idOfCr, String date, Double summa, Double summaTelCr, Double summaPr) {
        this.idOfCr = idOfCr;
        this.date = date;
        this.summa = summa;
        this.summaTelCr = summaTelCr;
        this.summaPr = summaPr;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdOfCr() {
        return idOfCr;
    }

    public void setIdOfCr(UUID idOfCr) {
        this.idOfCr = idOfCr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public Double getSummaTelCr() {
        return summaTelCr;
    }

    public void setSummaTelCr(Double summaTelCr) {
        this.summaTelCr = summaTelCr;
    }

    public Double getSummaPr() {
        return summaPr;
    }

    public void setSummaPr(Double summaPr) {
        this.summaPr = summaPr;
    }
}
