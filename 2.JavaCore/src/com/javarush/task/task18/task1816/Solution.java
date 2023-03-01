package com.javarush.task.task18.task1816;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) {
        args = "d:/1.txt".split(" ");

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int symbol;
            int count = 0;
            do {
                symbol = reader.read();
                if (Pattern.matches("[a-zA-z]", ((char) symbol) + "")) {
                    count++;
                }
            } while (symbol != -1);
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
