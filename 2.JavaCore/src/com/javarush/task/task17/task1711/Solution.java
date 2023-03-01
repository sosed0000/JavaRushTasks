package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    private static SimpleDateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private static SimpleDateFormat dateOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        //args = "-i  1   0".split(" +");
        //args = "-d  1   0".split(" +");
        //args = "-u 1 Вася м 15/04/1990 0 Гадя ж 12/01/1988".split(" ");
        //args = "-c Вася м 15/04/1990 Гадя ж 12/01/1988 Вася м 15/04/1990 ".split(" ");

        //if (args.length == 0) {return;}


        try {
            switch (args[0]) {

                case "-c":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i += 3) {
                            Person person;
                            String name = args[i];
                            Date bd = dateIn.parse(args[i + 2]);

                            if (args[i + 1].equals("м")) {
                                person = Person.createMale(name, bd);
                            } else {
                                person = Person.createFemale(name, bd);
                            }
                            allPeople.add(person);
                            System.out.println(allPeople.size() - 1);
                            // System.out.println(allPeople.get(allPeople.size() - 1).getName());
                            // System.out.println(allPeople.get(allPeople.size() - 1).getBirthDate());
                            // System.out.println(allPeople.get(allPeople.size() - 1).getSex());
                        }
                    }
                    break;

                case "-u":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i += 4) {

                            int id = Integer.parseInt(args[i]);
                            String name = args[i + 1];
                            Sex sex = args[i + 2].equals("м") ? Sex.MALE : Sex.FEMALE;
                            Date bd = dateIn.parse(args[i + 3]);

                            allPeople.get(id).setName(name);
                            allPeople.get(id).setSex(sex);
                            allPeople.get(id).setBirthDate(bd);

                            // System.out.println(allPeople.get(id).getName());
                            // System.out.println(allPeople.get(id).getBirthDate());
                            // System.out.println(allPeople.get(id).getSex());
                        }
                    }
                    break;

                case "-d":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {

                            int id = Integer.parseInt(args[i]);
                            allPeople.get(id).setName(null);
                            allPeople.get(id).setSex(null);
                            allPeople.get(id).setBirthDate(null);

                            // System.out.println(allPeople.get(id).getName());
                            // System.out.println(allPeople.get(id).getBirthDate());
                            // System.out.println(allPeople.get(id).getSex());
                        }
                    }
                    break;

                case "-i":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {

                            int id = Integer.parseInt(args[i]);
                            String name = allPeople.get(id).getName();
                            String sex = allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж";
                            String bd = dateOut.format(allPeople.get(id).getBirthDate());
                            System.out.println(name + " " + sex + " " + bd);
                        }
                    }
                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
