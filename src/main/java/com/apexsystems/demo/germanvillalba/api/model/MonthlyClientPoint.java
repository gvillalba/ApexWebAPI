package com.apexsystems.demo.germanvillalba.api.model;

import com.apexsystems.demo.germanvillalba.api.entity.Client;

import java.util.Map;

public class MonthlyClientPoint {
    private Client client;
    private Map<Integer, Long> monthlyPoints;

    public MonthlyClientPoint(Client client, Map<Integer, Long> monthlyPoints) {
        this.client = client;
        this.monthlyPoints = monthlyPoints;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Integer, Long> getMonthlyPoints() {
        return monthlyPoints;
    }

    public void setMonthlyPoints(Map<Integer, Long> monthlyPoints) {
        this.monthlyPoints = monthlyPoints;
    }
}
