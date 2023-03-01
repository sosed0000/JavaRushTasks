package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //напишите тут ваш код

        int id;
        Sex sex = null;



        //При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами в конец списка allPeople, и выводить id (index) на экран.
        if (args[0].equals("-c")) {
            id = allPeople.size();
            String[] dateForDate = args[3].split("/");
            Date date = new Date(Integer.parseInt(dateForDate[2]) - 1900, Integer.parseInt(dateForDate[1]) - 1, Integer.parseInt(dateForDate[0]));

            if (args[2].equals("м")) {
                allPeople.add(id, Person.createMale(args[1], date));
            } else if (args[2].equals("ж")) {
                allPeople.add(id, Person.createFemale(args[1], date));
            }
            System.out.println(allPeople.size() - 1);

        }

        //При запуске программы с параметром -r программа должна выводить на экран данные о человеке с заданным id по формату указанному в задании.
        if (args[0].equals("-r")) {
            SimpleDateFormat bd = new SimpleDateFormat("dd-MMM-yyy", Locale.ENGLISH);
            id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            System.out.println(person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж") + " " +
                    bd.format(person.getBirthDate()));
        }

        //При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
        if (args[0].equals("-u")) {
            id = Integer.parseInt(args[1]);
            if (args[3].equals("м")) {sex = Sex.MALE;}
            if (args[3].equals("ж")) {sex = Sex.FEMALE;}
            String[] dateForDate = args[4].split("/");
            Date date = new Date(Integer.parseInt(dateForDate[2]) - 1900, Integer.parseInt(dateForDate[1]) - 1, Integer.parseInt(dateForDate[0]));
            allPeople.get(id).setName(args[2]);
            allPeople.get(id).setSex(sex);
            allPeople.get(id).setBirthDate(date);
        }

        //При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
        if (args[0].equals("-d")) {
            id = Integer.parseInt(args[1]);
            allPeople.get(id).setName(null);
            allPeople.get(id).setSex(null);
            allPeople.get(id).setBirthDate(null);
        }
    }
}
