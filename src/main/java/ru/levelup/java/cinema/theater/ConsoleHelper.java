package ru.levelup.java.cinema.theater;

import java.util.Scanner;

/**
 * Класс обеспечивающий взаимодействие с пользователем
 */
public class ConsoleHelper {
    public  void printToConsole(String message){
        System.out.print(message);
    }

    public  void printlnToConsole(String message){
        System.out.println(message);
    }

    public  void printlnToConsole(Object obj){
        System.out.println(obj.toString());
    }

    public int getIntValueFromConsole(){
        int value = -1;
        Scanner scanner = new Scanner(System.in);
        value = scanner.nextInt();

        // !!! когда закрывается scanner он закрывает System.in и повторно сосчитать уже не получается
        // НЕ ЗАКРЫВАТЬ SCANNER
        // When a Scanner is closed, it will close its input source if the source implements the Closeable interface.
        return value;

    }
}
