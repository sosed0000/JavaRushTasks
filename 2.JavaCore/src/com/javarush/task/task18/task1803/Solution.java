package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePassName = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(filePassName);


        HashMap<Integer, Integer> input = new HashMap<>();

       do {
           int val = inputStream.read();
           if (input.containsKey(val)) {
               input.replace(val, (input.get(val) + 1));
           } else input.put(val, 1);
       } while (inputStream.available() > 0);
        inputStream.close();


        int maxQuantity = input.values().stream().max(Integer :: compare).get();

        Set<Map.Entry<Integer, Integer>>  entries = input.entrySet();

        for (Map.Entry entry:
             entries) {
            if ((int) entry.getValue() == maxQuantity) {
                System.out.print(entry.getKey() + " ");
            }
        }

    }
}
