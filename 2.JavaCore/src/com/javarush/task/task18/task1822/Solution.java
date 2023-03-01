package com.javarush.task.task18.task1822;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) {


        if (args.length == 0) {
            return;
        }


        String fileName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
           while (fileReader.ready()) {
               String product = fileReader.readLine();
               if (product.startsWith(args[0] + " ")) {
                   System.out.println(product);
                   break;
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
