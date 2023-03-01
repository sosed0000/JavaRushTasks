package com.javarush.task.task18.task1807;

import java.io.*;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        String filePassName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            filePassName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        try (FileInputStream inputStream = new FileInputStream(filePassName)) {
            while (inputStream.available() > 0) {

                if (inputStream.read() == (int) ',') {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
