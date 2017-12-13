package ru.levelup.java.cinema.theater.entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


/**
 * Класс обеспечивающий тестирование базовой функциональности class Client
 */
public class ClientTest {

    private Client client;
    private String name = "TEST";
    ArrayList<Ticket> tickets = new ArrayList<>();

    @Before
    public void init () {
        tickets.add(new Ticket(1));
        tickets.add(new Ticket(2));
        tickets.add(new Ticket(3));
        tickets.add(new Ticket(4));
        tickets.add(new Ticket(5));

        client = new Client(name, tickets);
    }

    @Test
    public void checkEqualsTickets() {
        boolean equal =  client.getTickets().equals(tickets);
        Assert.assertTrue("Не совпадают массивы", equal);
    }

    @Test
    public void checkInitNames() {
        checkEqualsNames(name);
    }

    private void checkEqualsNames(String testName) {
        boolean equal =  client.getName().equals(testName);
        Assert.assertTrue("Не совпадает имя клиента", equal);
    }

    @Test
    public void checkSetName() {
        String testName = "test";
        client.setName(testName);
        checkEqualsNames(testName);
        client.setName(name);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
        tickets.clear();
    }
}
