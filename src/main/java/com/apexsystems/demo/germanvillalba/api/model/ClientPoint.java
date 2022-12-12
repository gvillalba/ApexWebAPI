package com.apexsystems.demo.germanvillalba.api.model;

import com.apexsystems.demo.germanvillalba.api.entity.Client;

public class ClientPoint {
    private Client client;
    private long points;

    public ClientPoint(Client client, long points) {
        this.client = client;
        this.points = points;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}
