package com.pulsaractivo.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import com.pulsaractivo.model.Device;

@Entity
public class Client extends Person implements Serializable {

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "client")
    private List<Device> devices;

    protected Client() {
        this.devices = new ArrayList<Device>();
    }

    public Client(String name, String identifier) {
        super(name, identifier);
        this.devices = new ArrayList<Device>();
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (client)", super.getName(), super.getIdentifier());
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
