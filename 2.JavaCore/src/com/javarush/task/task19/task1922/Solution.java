package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {

        words.add("файл");
        words.add("вид");
        words.add("В");


    }

    public static void main(String[] args) {

        String file = "";
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line:
             data) {
            int count = 0;
            String[] words = line.split(" ");
            for (String word:
                 words) {
                if (Solution.words.contains(word)) {
                    count++;
                }
            }
            if (count == 2) {
                System.out.println(line);
            }
        }

    }
}
