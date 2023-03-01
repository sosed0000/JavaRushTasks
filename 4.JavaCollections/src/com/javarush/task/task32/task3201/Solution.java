package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) {
  //      args = new String[] {"d:\\1.txt", "5", "sdfgsdf"};
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            if (randomAccessFile.length() < number) {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(text.getBytes());
            } else {
                randomAccessFile.seek(number);
                randomAccessFile.write(text.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
