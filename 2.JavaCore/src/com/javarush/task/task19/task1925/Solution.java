package com.javarush.task.task19.task1925;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {
      //   args = "d:/1.txt d:/2.txt".split(" ");
        if (args.length == 0) {return;}

        String file1 = args[0];
        String file2 = args[1];

        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            while (reader.ready()) {
                data.addAll(Arrays.asList(reader.readLine().split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //data.forEach(System.out::println);

        StringBuilder result = new StringBuilder();
        for (String s:
             data) {
            if (s.length() > 6 ) {
                result.append(s).append(",");
            }
        }
        if (!result.isEmpty()) {
            result.replace(result.lastIndexOf(","), result.lastIndexOf(",") + 1, "");
        }
    //    System.out.println(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            writer.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
