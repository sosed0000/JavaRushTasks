package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String outputFileName = reader.readLine();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName)); // d:\2.txt
        String line;
        do {
            line = reader.readLine();
            writer.write(line + "\n");
        }
        while (!line.equals("exit"));

        reader.close();
        writer.close();
    }
}
