package com.javarush.task.pro.task15.task1504;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код

        try (Scanner scanner = new Scanner(System.in);
             InputStream input = Files.newInputStream(Path.of(scanner.nextLine()));
             OutputStream output = Files.newOutputStream(Path.of(scanner.nextLine()));) {
            while (input.available() > 0) {
                int first = input.read();
                if (input.available() > 0) {
                    int second = input.read();
                    output.write(second);
                }
                output.write(first);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

