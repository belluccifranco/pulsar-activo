package com.pulsaractivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imei;
    private String name;

    protected Device() {
    }

    public Device(String imei, String name) {
        this.imei = imei;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", name, imei);
    }

    public long getId() {
        return id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
