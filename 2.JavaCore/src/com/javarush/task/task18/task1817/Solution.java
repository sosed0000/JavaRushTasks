package com.javarush.task.task18.task1817;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) {
        args = "d:/1.txt".split(" ");

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int symbol;
            int count = -1;
            int spaceCount = 0;
            do {
                symbol = reader.read();
                if (Character.isSpaceChar((char) symbol)) {
                    spaceCount++;
                }
                count++;

            } while (symbol != -1);
            double result = (double) spaceCount / count * 100;
           // result = result / 100;
            System.out.println( result);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
