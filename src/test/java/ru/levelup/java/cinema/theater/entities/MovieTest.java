package ru.levelup.java.cinema.theater.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Клас тестирующий класс Movie
 */

public class MovieTest {

    private Movie movie;
    private static final String title = "Заголовок";
    private static final int duration = 120;
    private static final String description = "test";
    private static final String hallName = "test";
    private static final int hallSize = 120;
    private static final double hallPrice = 120;
    private static final String desc = "test test";
    private static final String titleT = "test test";
    private static final int durationT = 22;
    private static final String patternString = "Наименование='" + title + '\'' +
                                                ", Длительность=" + duration +
                                                ", Описание='" + description + '\'';
    private List<Session> sessions;

    @Before
    public void setUp() throws Exception {
        movie = new Movie(title, duration, description);
        sessions = new ArrayList<Session>();
        sessions.add(new Session(new Hall(hallName, hallSize), new Date(), hallPrice));
    }

    @Test
    public void checkGetTitle() {
        Assert.assertTrue("Проверка возвращаемого имени", movie.getTitle().equals(title));
    }

    @Test
    public void checkGetDescription() {
        Assert.assertTrue("Проверка возвращаемого описания", movie.getDescription().equals(description));
    }

    @Test
    public void checkConstructor() {
        Assert.assertTrue("Проверка инициализации списка в конструкторе", movie.getSessions().size() == 0);
    }

    @Test
    public void checkSetDescription() {
        movie.setDescription(desc);
        Assert.assertTrue("Проверка задания описания", movie.getDescription().equals(desc));
        movie.setDescription(description);
    }

    @Test
    public void checkSetTitle() {
        movie.setTitle(titleT);
        Assert.assertTrue("Проверка задания имени", movie.getTitle().equals(titleT));
        movie.setDescription(title);
    }

    @Test
    public void checkGetDuration() {
        Assert.assertTrue("Проверка возвращаемой длительности", movie.getDuration() == duration);
    }

    @Test
    public void checkSetDuration() {
        movie.setDuration(durationT);
        Assert.assertTrue("Проверка задания имени", movie.getDuration() == durationT);
        movie.setDuration(duration);
    }

    @Test
    public void checkAddSession() {
        Session session = new Session(new Hall(hallName + "2", hallSize), new Date(), hallPrice);
        Ticket ticket = new Ticket(1);
        session.getTickets().add(ticket);
        int count = movie.getSessions().size();
        movie.addSession(session);
        Assert.assertTrue("Проверка добавления сессии", movie.getSessions().size() == count + 1);

        session.getTickets().clear();
        Ticket ticket2 = new Ticket(2);
        session.getTickets().add(ticket2);
        Assert.assertTrue("Проверка не добавления сессии c такими же параметрами что уже есть", movie.getSessions().size() == count + 1);

        movie.getSessions().clear();
    }

    @Test
    public void checkToString() {
        String out = movie.toString();
        Assert.assertTrue("Проверка преобразования в текстовую строку", out.contains(patternString));

    }
}
