package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        //напишите тут ваш код

        String s = url.contains("?") ? url.substring(url.indexOf('?') + 1) : "";
        List<String> parameters = Arrays.asList(s.split("&"));
        List<String> parameters1 = new ArrayList<>();
        parameters.forEach(s1 -> parameters1.add(s1 = s1.contains("=") ? s1.substring(0, s1.indexOf("=")) : s1));
        for (int i = 0; i < parameters1.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(parameters1.get(i));
        }

        boolean isContainsObj = false;
        String obj = null;
        for (String p:
             parameters) {
            if (Pattern.matches("obj.*", p)) {
                isContainsObj = true;
                obj = p;
                System.out.println();
            }
        }
        if (isContainsObj) {
            obj = obj.substring(obj.indexOf("=") + 1);
            try {
                double d = Double.parseDouble(obj);
                alert(d);
            }
            catch (Exception e) {
                alert(obj);
            }
        }

    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
