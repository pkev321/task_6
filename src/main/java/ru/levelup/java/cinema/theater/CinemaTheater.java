package ru.levelup.java.cinema.theater;

//import javafx.application.Application;
import ru.levelup.java.cinema.theater.entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  Класс описывает работу терминала продаж в кинозале
 */
public class CinemaTheater {

    private ConsoleHelper consoleHelper;
    private List<Movie> movies;
    private List<Food> menu;
    private List<Integer> order;

    /**
     * Точка входа
     */
    public static void main(String[] args) {
        CinemaTheater cinemaTheater = new CinemaTheater();
        cinemaTheater.consoleHelper = new ConsoleHelper();
        cinemaTheater.readData();
        cinemaTheater.readBarMenu();
        cinemaTheater.initMenu();

    }

    /**
     * Отображение меню
     */
    private void initMenu(){
        consoleHelper.printlnToConsole("Выберите пунк меню");
        consoleHelper.printlnToConsole("1 - Показать информацию о сеансах");
        consoleHelper.printlnToConsole("2 - Покупка билета");
        consoleHelper.printlnToConsole("3 - Заказ еды");
        consoleHelper.printlnToConsole("0 - Выключить");
        consoleHelper.printlnToConsole("Выберите: ");
        int menu = consoleHelper.getIntValueFromConsole();
        switch (menu) {
            case (1):
                sessionInformation();
                break;
            case (2):
                ticketSelling();
                break;
            case(3):
                barInformation();
                break;
            case(0):
                return;
            default:
                return;
        }
        // рекурсия
        initMenu();
    }

    /**
     * Отображение информации о имеющихся билетах и сеансах
     */
    private void sessionInformation() {

        for (Movie movie : movies ) {
            consoleHelper.printlnToConsole(movie);
        }
    }

    private void barInformation() {
        int count = 1;
        consoleHelper.printlnToConsole("Выберите что будете брать (0 - отмена)");
        for (Food food: menu ) {
            consoleHelper.printToConsole(count + "  : ");
            consoleHelper.printlnToConsole(food);
            count++;
        }
        buyFood();
    }

    /**
     * Чтение меню бара
     */
    private void readBarMenu() {
        menu = new ArrayList<Food>();

        final String FILE_NAME = "res/barMenu.txt"; // Файл содержащий меню бара

        try {

            Scanner sc = new Scanner(new File(FILE_NAME)).useDelimiter(";");

            while (sc.hasNext()) {
                String name = sc.next().trim();
                double price = sc.nextDouble();
                Food food = new Food(name, price);
                menu.add(food);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Процедура покупки еды
     */
    private void buyFood() {
        consoleHelper.printlnToConsole("Что вы будите заказывать?");

        int numPosition = consoleHelper.getIntValueFromConsole();
        if (numPosition == 0 || numPosition > menu.size())
            return;
        double price = menu.get(numPosition-1).getPrice();
        consoleHelper.printlnToConsole("С Вас " + price + " зайчиков.");

        int bunnies = consoleHelper.getIntValueFromConsole();

        if (bunnies < price)
            consoleHelper.printlnToConsole("Скупой повторяет ввод, повторите. \n");
        if (bunnies == price)
            consoleHelper.printlnToConsole("Ok! \n Приятного аппетита!\n \n ");
        if (bunnies > price) {
            consoleHelper.printlnToConsole("Приятного аппетита! \n Сдачу получите на выходе из зала ...\n \n ");
        }
    }

    /**
     * Чтение данных во нутреннюю структуру
     */
    private void readData() {
        movies = new ArrayList<Movie>();

        final String FILE_NAME = "res/settings.txt"; // Файл содержащий структуру данных
        //Data struct in file settings: title;duration;description;session{startTime;price;Hall{name,amountOfPlace};Ticket{place}}
        try {

            Scanner sc = new Scanner(new File(FILE_NAME)).useDelimiter(";");

            while (sc.hasNext()) {
                String movTitle = sc.next().trim();
                int movDurat = Integer.parseInt(sc.next());
                String movDesc = sc.next();
                Date sesSTime = new Date();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                    sesSTime = formatter.parse(sc.next());
                }
                catch (ParseException e)  {

                }
                double sesPrice = sc.nextDouble();
                String hallName = sc.next();
                int hallAPlace = Integer.parseInt(sc.next());
                int ticketNom = Integer.parseInt(sc.next());
                Hall hall = new Hall(hallName,hallAPlace );
                Ticket ticket = new Ticket(ticketNom);

                Session session = new Session(hall, sesSTime, sesPrice );
                session.getTickets().add(ticket);

                Movie movie = new Movie(movTitle, movDurat, movDesc);
                movie.getSessions().add(session);
                addMovie(movie);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция осущствляет формирование структуры, специально для чтения данных из файла
     * @param movie струтура содержащаяся в одной строке файла настроек
     */
    private void addMovie(Movie movie) {
        boolean newMovie = true;
        for (Movie mov : movies) {
            if (mov.getTitle().equals(movie.getTitle())) {
                mov.addSession(movie.getSessions().get(0));
                newMovie = false;
            }
        }
        if (newMovie) {
            movies.add(movie);
        }
    }

    /**
     * Процедура продажи билетов
     */
    private void ticketSelling() {
        consoleHelper.printlnToConsole("");
        // выбор фильма
        consoleHelper.printlnToConsole("Укажите номер фильма который Вы хотели бы посмотреть:");
        int i = 0;
        for (Movie mov : movies) {
            consoleHelper.printlnToConsole( i + " :" + mov.getTitle() + ";" );
            i++;
        }
        int movNumber = consoleHelper.getIntValueFromConsole();
        if (movNumber > movies.size()-1 || movNumber < 0 ) {
            consoleHelper.printlnToConsole("Неправильный выбор, повторите. \n");
            return;
        }

        // selection session
        consoleHelper.printlnToConsole("Выберите номер сеанса:");
        int i2 = 0;
        for (Session ses : movies.get(movNumber).getSessions()) {
            consoleHelper.printlnToConsole( i2 + " : Зал[" + ses.getHall() + "] Время[" + ses.getStartTime() + "] Доступное количество мест = " + ses.getAmountOfAvailablePlaces() + ";" );
            i2++;
        }
        int sesNumber = consoleHelper.getIntValueFromConsole();
        if (sesNumber > movies.get(movNumber).getSessions().size()-1 || movNumber < 0 ) {
            consoleHelper.printlnToConsole("Неправильный выбор, повторите. \n");
            return;
        }

        // selection amount of tickets
        int amountOfAvailablePlaces = movies.get(movNumber).getSessions().get(sesNumber).getAmountOfAvailablePlaces();
        consoleHelper.printlnToConsole("Сколько билетов Вам надо? (Доступно только " + amountOfAvailablePlaces + "  мест)");
        int needTickets = consoleHelper.getIntValueFromConsole();

        if (needTickets > amountOfAvailablePlaces) {
            consoleHelper.printlnToConsole("Зал не резиновый.");
            consoleHelper.printlnToConsole("Неправильный выбор, повторите. \n");
            return;
        }

        double price = needTickets * movies.get(movNumber).getSessions().get(sesNumber).getPrice();
        consoleHelper.printlnToConsole("Дайте мне денег побольше!!!  (не менее " + price + "  зайчиков)");
        int bunnies = consoleHelper.getIntValueFromConsole();

        if (bunnies < price)
            consoleHelper.printlnToConsole("Скупой повторяет ввод, повторите. \n");
        if (bunnies == price) {
            consoleHelper.printlnToConsole("Ok!");
            movies.get(movNumber).getSessions().get(sesNumber).saleTicket(needTickets);
        }
        if (bunnies > price) {
            consoleHelper.printlnToConsole("Сдачу получите на выходе из зала ...");
            movies.get(movNumber).getSessions().get(sesNumber).saleTicket(needTickets);
        }
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("СВОБОДНЫЙ ТЕРМИНАЛ !!!");
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("");
    }
}
