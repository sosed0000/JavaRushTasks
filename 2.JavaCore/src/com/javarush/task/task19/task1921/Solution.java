package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
      //  args = "d:/1.txt".split(" ");

        if (args.length == 0) return;

        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line:
             data) {
            String[] lines = line.split(" ");
            int year = Integer.parseInt(lines[lines.length - 1]);
            int month = Integer.parseInt(lines[lines.length - 2]);
            int day = Integer.parseInt(lines[lines.length - 3]);
            String name = "";
            for (int i = 0; i < lines.length - 3; i++){
                name = name + lines[i] + " ";
            }
            name = name.trim();
            PEOPLE.add(new Person(name, new Date(year - 1900, month - 1, day)));
        }
        PEOPLE.forEach(person -> System.out.println(person.getName() + " " + person.getBirthDate()));
    }
}
