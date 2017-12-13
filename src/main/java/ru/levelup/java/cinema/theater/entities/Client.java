package ru.levelup.java.cinema.theater.entities;

import java.util.List;

public class Client {
    private String name;
    private List<Ticket> tickets;

    public Client(String name, List<Ticket> tickets) {
        this.name = name;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
