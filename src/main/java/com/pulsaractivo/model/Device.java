package com.pulsaractivo.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import com.pulsaractivo.model.DeviceType;
import com.pulsaractivo.model.ProviderType;
import com.pulsaractivo.model.Event;
import com.pulsaractivo.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imei;
    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private List<Event> events;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    protected Device() {
        this.events = new ArrayList<Event>();
    }

    public Device(String imei, String name, DeviceType type) {
        this.imei = imei;
        this.name = name;
        this.type = type;
        this.events = new ArrayList<Event>();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addEvent(Event event) {
        if (event.getDevice() == null) {
            event.setDevice(this);
        }

        if (!this.events.contains(event)) {
            this.events.add(event);
        }
    }

    /*public Event getLastEvent() {
        Iterator<Event> iterator = this.events.iterator();
        Event maxDateTimeEvent = null;
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (maxDateTimeEvent == null) {
                maxDateTimeEvent = event;
            } else {
                if (event.getDateTime().isAfter(maxDateTimeEvent.getDateTime())) {
                    maxDateTimeEvent = event;
                }
            }
        }
        return maxDateTimeEvent;
    }*/
}
