package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        //      args = new String[] {"d:\\1.txt", "5", "12345"};
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        byte[] b = new byte[text.length()];


        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(number);
            raf.read(b, 0, text.length());
            String s = new String(b);


            if (s.equals(text)) {

                raf.seek(raf.length());
                raf.write("true".getBytes());
            } else {
                raf.seek(raf.length());
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
