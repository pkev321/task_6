package ru.levelup.java.cinema.theater.entities;

/**
 * Класс описывающий билет
 */
public class Ticket {
    private int place;

    public Ticket(int place) {
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Билет{" +
                place +
                "}\n";
    }
}
