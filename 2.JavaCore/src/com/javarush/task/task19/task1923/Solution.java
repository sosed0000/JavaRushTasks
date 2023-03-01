package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) {
   //     args = "d:/1.txt d:/2.txt".split(" ");
        if (args.length == 0) {return;}

        String file1 = args[0];
        String file2 = args[1];

        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            while (reader.ready()) {
              //  data.addAll(Arrays.stream(reader.readLine().split(" ")).toList());

                data.addAll(Arrays.asList(reader.readLine().split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = new StringBuilder();
        for (String word:
                data) {
            if (word.matches(".*\\d.*")) {
                result.append(" ").append(word);
            };
        }
        result = new StringBuilder(result.toString().trim());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            writer.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
