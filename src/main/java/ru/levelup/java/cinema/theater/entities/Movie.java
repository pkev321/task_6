package ru.levelup.java.cinema.theater.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывающий кино
 */

public class Movie {

    private List<Session> sessions;
    private String title;
    private int duration;
    private String description;

    public Movie(String title, int duration, String description) {
        this.sessions = new ArrayList<Session>();
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public Movie(List<Session> sessions, String title, int duration, String description) {
        this.sessions = sessions;
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Наименование='" + title + '\'' +
                ", Длительность=" + duration +
                ", Описание='" + description + '\'' +
                ", Сеансы= \n" + sessions;
    }

    /**
     * Добавление сеанса с проверкой повтора сеанса в имеющемся наборе
     * @param session Структура "сеанс" с билетами
     */
    public void  addSession(Session session) {
        boolean newSession = true;

        for (Session ses: sessions) {
            if (ses.getHall().getName().equals(session.getHall().getName()) && ses.getStartTime().toString().equals(session.getStartTime().toString())) {
                ses.getTickets().addAll(session.getTickets());
                newSession = false;
            }
        }
        if (newSession) {
            sessions.add(session);
        }
    }
}
