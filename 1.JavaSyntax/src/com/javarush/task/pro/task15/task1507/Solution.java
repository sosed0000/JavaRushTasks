package com.javarush.task.pro.task15.task1507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/* 
Пропускаем не всех
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        try (var console = new Scanner(System.in)) {
            var input = Files.readAllLines(Path.of(console.nextLine()));

            for (int i = 0; i < input.size(); i+=2) {
                System.out.println(input.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

