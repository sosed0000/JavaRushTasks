package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        PersonScannerAdapter(Scanner scanner) {
            fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {

            String[] data = fileScanner.nextLine().split(" ");
            String firstName = data[1];
            String middleName = data[2];
            String lastName = data[0];
            int year = Integer.parseInt(data[5]) - 1900;
            int month = Integer.parseInt(data[4]) - 1;
            int day =  Integer.parseInt(data[3]);

            Date birthDate = new Date(year, month, day);

            return new Person(firstName, middleName, lastName, birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
