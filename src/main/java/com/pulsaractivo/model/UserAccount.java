package com.pulsaractivo.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @NotEmpty
    @Length(max = 250)
    private String cpassword;

    @JoinTable(name = "userAccount_userRole",
            joinColumns = {
                    @JoinColumn(name = "id_userAccount", referencedColumnName = "id_userAccount")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_userRole", referencedColumnName = "id_userRole")})
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<UserRole> userRoles;


    public UserAccount() {
    }

    public UserAccount(String username, String password, String cpassword, List<UserRole> userRoles) {
        this.username = username;
        this.password = password;
        this.cpassword = cpassword;
        this.userRoles = userRoles;
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

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
