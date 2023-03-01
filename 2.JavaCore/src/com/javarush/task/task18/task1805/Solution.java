package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePassName = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(filePassName);

        Set<Integer> inputSet = new TreeSet<Integer>();

        while (inputStream.available() > 0) {
            inputSet.add(inputStream.read());
        }

        inputStream.close();

        System.out.println(inputSet.toString().substring(1, inputSet.toString().length() -  1).replaceAll(",", ""));
    }
}
