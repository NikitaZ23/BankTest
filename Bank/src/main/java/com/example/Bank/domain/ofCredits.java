package com.example.Bank.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ofCredits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID id_cl, id_cr;
    private Double summa;
    private int month;

    public ofCredits() {
    }

    public ofCredits(UUID id_cl, UUID id_cr, Double summa, int month) {
        this.id_cl = id_cl;
        this.id_cr = id_cr;
        this.summa = summa;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_cl() {
        return id_cl;
    }

    public void setId_cl(UUID id_cl) {
        this.id_cl = id_cl;
    }

    public UUID getId_cr() {
        return id_cr;
    }

    public void setId_cr(UUID id_cr) {
        this.id_cr = id_cr;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }
}
