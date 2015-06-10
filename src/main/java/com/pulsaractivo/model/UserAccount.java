package com.pulsaractivo.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_userAccount")
    private long id;

    @Email
    @NotEmpty
    @Length(max = 250)
    private String username;

    @NotEmpty
    @Length(max = 250)
    private String password;

    public UserAccount() {
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
