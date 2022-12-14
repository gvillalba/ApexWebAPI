package com.apexsystems.demo.germanvillalba.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="clients")
public class Client extends BaseEntity {

    private String firstname;
    private String lastname;
    private String ssn;

    public Client() {
        super();
    }

    public Client(long id, String firstname, String lastname, String ssn) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
