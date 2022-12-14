package com.apexsystems.demo.germanvillalba.api.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction extends BaseEntity{

    @Column
    private Date date;

    @Column
    private long amount;

    @ManyToOne(targetEntity = Client.class)
    private Client client;

    public Transaction() {
        super();
        this.date = new Date();
    }

    public Transaction(long id, Date date, long amount, Client client) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
