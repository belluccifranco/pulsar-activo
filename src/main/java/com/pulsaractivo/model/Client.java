package com.pulsaractivo.model;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Client extends Person implements Serializable {

    protected Client() {
    }

    public Client(String name, String identifier) {
        super(name, identifier);
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (client)", super.getName(), super.getIdentifier());
    }
}
