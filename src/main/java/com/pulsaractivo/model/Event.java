package com.pulsaractivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.pulsaractivo.model.EventType;
import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate dateTime;
    @Enumerated(EnumType.STRING)
    private EventType type;
    private double lat;
    private double lng;

    protected Event() {
    }

    public Event(LocalDate dateTime, EventType type, double lat, double lng) {
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
}
