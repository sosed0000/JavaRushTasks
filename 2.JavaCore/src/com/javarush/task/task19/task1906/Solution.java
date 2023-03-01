package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {

        String file1 = "";
        String file2 = "";


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(file1);
             FileWriter writer = new FileWriter(file2)) {
            reader.read();
            while (reader.ready()) {
                writer.write(reader.read());
                reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
