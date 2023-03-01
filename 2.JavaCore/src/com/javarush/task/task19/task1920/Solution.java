package com.javarush.task.task19.task1920;

import javafx.collections.transformation.SortedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
*/

public class Solution {
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

        Double maxValue = Double.MIN_VALUE;
        for (Double val:
             result.values()) {
            maxValue = Math.max(maxValue, val);
        }
        for (Map.Entry<String, Double> entry:
             result.entrySet()) {
            if (entry.getValue().equals(maxValue)) {
                System.out.println(entry.getKey());
            }
        }

    }
}

