package ru.levelup.java.cinema.theater.entities;

/**
 * Класс описывающий кинозал (помещение)
 */
public class Hall {
    private String name;
    private int amountOfPlace;

    public Hall() {
        this("", 0);
    }

    public Hall(String name, int amountOfPlace) {
        this.name = name;
        this.amountOfPlace = amountOfPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfPlace() {
        return amountOfPlace;
    }

    public void setAmountOfPlace(int amountOfPlace) {
        this.amountOfPlace = amountOfPlace;
    }

    @Override
    public String toString() {
        return "Зал{" +
                "Наименование='" + name + '\'' +
                ", Доступное число мест=" + amountOfPlace +
                '}';
    }
}
