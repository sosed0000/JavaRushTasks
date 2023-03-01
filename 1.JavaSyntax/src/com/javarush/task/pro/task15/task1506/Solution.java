package com.javarush.task.pro.task15.task1506;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try (var console = new Scanner(System.in)) {
             var input = Files.readAllLines(Path.of(console.nextLine()));
            for (String s:
                 input) {
                System.out.println(s.replaceAll("[., ]", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

