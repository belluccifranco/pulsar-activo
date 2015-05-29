package com.pulsaractivo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Dispatcher extends Person implements Serializable {

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "dispatcher")
    private List<Event> events;

    protected Dispatcher() {
        this.events = new ArrayList<Event>();
    }

    public Dispatcher(String name, String identifier) {
        super(name, identifier);
        this.events = new ArrayList<Event>();
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (dispatcher)", super.getName(), super.getIdentifier());
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
