package com.pulsaractivo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.EventType;
import java.time.LocalDate;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imei;
    private LocalDate dateTime = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private EventType type;
    private double lat;
    private double lng;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_dispatcher", referencedColumnName = "id", nullable = true)
    private Dispatcher dispatcher;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_device", referencedColumnName = "id", nullable = true)
    private Device device = null;

    protected Event() {
    }

    public Event(Device device, LocalDate dateTime, EventType type, double lat, double lng) {
        this.device = device;
        this.dateTime = dateTime;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) [%f, %f]", type.toString(), dateTime.toString(), lat, lng);
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

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
        device.addEvent(this);
    }
}
