package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) {

     //   args = "d:/1.txt".split(" ");
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, Double> result = new TreeMap<>();

        for (String line :
                data) {
            String name = line.split(" ")[0];
            Double value = Double.parseDouble(line.split(" ")[1]);
            if (result.containsKey(name)) {
                result.replace(name, result.get(name) + value);
            } else {
                result.put(name, value);
            }

        }
        result.forEach( (s, d) -> System.out.println(s + " " + d));


    }
}
