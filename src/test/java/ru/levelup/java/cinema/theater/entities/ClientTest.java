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
    private final static String name = "TEST";
    private final static String testName = "test";
    private final static int[] number = {1, 2, 3, 4, 5, 6};
    ArrayList<Ticket> tickets = new ArrayList<>();

    @Before
    public void init () {
        for (int i = 0; i < number.length; i++)
            tickets.add(new Ticket(number[i]));

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
