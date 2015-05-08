package com.pulsaractivo.model;

import java.io.Serializable;
import java.util.List;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.Pattern;
//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
    @NamedQuery(name = "Device.allDevices", query = "SELECT d FROM Device d"),
    @NamedQuery(name = "Device.deviceById", query = "SELECT d FROM Device d WHERE d.id = :id")

})
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Device() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
