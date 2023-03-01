package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePassName = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(filePassName);
        int min = inputStream.read();
        while (inputStream.available() > 0) {
            min = Math.min(min, inputStream.read());
        }
        inputStream.close();

        System.out.println(min);
    }

}
