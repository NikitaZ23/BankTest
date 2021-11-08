package com.example.Bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Credits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double limitMin, limitMax, proc;

    public Credits() {
    }
    public Credits(double limitMin, double limitMax, double proc) {
        this.limitMin = limitMin;
        this.limitMax = limitMax;
        this.proc = proc;
    }

    public double getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(double limitMin) {
        this.limitMin = limitMin;
    }

    public double getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(double limitMax) {
        this.limitMax = limitMax;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public double getProc() {
        return proc;
    }

    public void setProc(double proc) {
        this.proc = proc;
    }
}
