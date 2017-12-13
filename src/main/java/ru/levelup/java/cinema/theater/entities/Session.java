package ru.levelup.java.cinema.theater.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Класс описывающий КмноСеанс
 */

public class Session {
    private Hall hall;
    private Date startTime;
    private double price;
    private List<Ticket> tickets; // equals private List<Ticket> tickets = null;

    public Session(Hall hall, Date startTime, double price) {
        this.hall = hall;
        this.startTime = startTime;
        this.price = price;
        this.tickets = tickets;
        this.tickets = new ArrayList<>();
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Продажа билетов
     * @param count количество билетов
     */
    public void saleTicket(int count)
    {
        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(tickets.size()));
        }
    }

    /**
     * Получение количества доступных мест
     * @return
     */
    public int getAmountOfAvailablePlaces() {
        return hall.getAmountOfPlace() - tickets.size();
    }

    @Override
    public String toString() {
        // форматируем строку
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String sStartTime = df.format(startTime);

        return "Сеанс{" +
                "Зал=" + hall +
                ", Время начала=" + sStartTime +
                ", Цена=" + price +
                ", Доступно мест=" + getAmountOfAvailablePlaces() +
                "}\n";
    }
}
