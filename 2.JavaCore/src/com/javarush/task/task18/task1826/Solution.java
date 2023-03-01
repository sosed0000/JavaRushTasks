package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) {

        if (args.length == 0) {
            return;
        }

        String key = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];

        try (FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream = new FileOutputStream(fileOutputName)) {

            if (key.equals("-e")) {
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read() + 1);
                }
            }

            if (key.equals("-d")) {
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read() - 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
