package com.javarush.task.task18.task1820;

import java.io.*;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) {
        String file1Name = "";
        String file2Name = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1Name = reader.readLine();
            file2Name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file1Name));
             FileWriter writer = new FileWriter(file2Name)) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
            String[] nums = sb.toString().split(" ");
            sb = new StringBuilder();
            for (String num :
                    nums) {
                sb.append(Math.round(Double.parseDouble(num))).append(" ");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}