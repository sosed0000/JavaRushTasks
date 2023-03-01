package com.javarush.task.task19.task1909;

import org.w3c.dom.DOMStringList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) {

        String file1 = "";
        String file2 = "";
        List<String> data = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
            for (String line :
                    data) {
                writer.write(line.replaceAll("\\.", "!") + "\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
