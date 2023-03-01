package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) {
        String file1 = "";
        String file2 = "";
        String dataString = "";


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            while (reader.ready()) {
                dataString = dataString + (reader.readLine());
            }
            String[] data = dataString.split(" ");
            for (String s:
                    data) {
                try {
                    writer.write(Integer.parseInt(s) + " ");
                } catch(NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
