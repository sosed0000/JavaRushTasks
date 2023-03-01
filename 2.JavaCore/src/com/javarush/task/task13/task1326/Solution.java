package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.*;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        FileInputStream fileInputStream = new FileInputStream(new File(scanner.nextLine()));
        scanner = new Scanner(fileInputStream);
        List<Integer> result = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            if (x % 2 == 0) {
                result.add(x);
            }
        }

        Collections.sort(result);
        result.forEach(integer -> System.out.println(integer));
        scanner.close();
        fileInputStream.close();

    }
}
