package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {

        String file1 = "";
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            while (reader.ready()) {
                list.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";


        for (int i = 0; i < list.size(); i++) {
            /*
            StringBuilder result = new StringBuilder(list.get(i));
            Pattern p = Pattern.compile("\\s\\d+\\D");
            Matcher m = p.matcher(result);
            while (m.find()) {
                int num = Integer.parseInt(result.substring(m.start() + 1, m.end() -1));
                if (num <= 12) {
                    m = p.matcher(result.replace(m.start() + 1, m.end() -1, map.get(num)));
                }
            }

             */
            result = list.get(i);
            for (Map.Entry<Integer, String> entry:
                 map.entrySet()) {

                 result = result.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());

            }
            System.out.println(result);
        }


    }
}
