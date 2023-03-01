package com.javarush.task.task18.task1821;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "d:/1.txt";

        try (FileReader reader = new FileReader(args[0])) {
            SortedMap<Integer, Integer> chars = new TreeMap<>();
            while (reader.ready()) {
                int curChar = reader.read();
                if (chars.containsKey(curChar)) {
                    chars.replace(curChar, chars.get(curChar) + 1);
                } else chars.put(curChar, 1);
            }
            chars.forEach((integer, integer2) -> System.out.println((char) (int) integer + " " + integer2));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
