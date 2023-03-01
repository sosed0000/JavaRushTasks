package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String[] a = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] b = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        Map<String, Integer> romanIntMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            romanIntMap.put(a[i], b[i]);
        }
        int result = 0;
        String[] romanNumbers = s.toUpperCase().split("");
        for (int i = 0; i < romanNumbers.length; i++) {
            int n = romanIntMap.get(romanNumbers[i]);
            if (i < romanNumbers.length - 1) {
                int n1 = romanIntMap.get(romanNumbers[i + 1]);
                if (n < n1) {
                    result += romanIntMap.get(romanNumbers[i].concat(romanNumbers[++i]));
                    continue;
                }
            }
            result += romanIntMap.get(romanNumbers[i]);

        }
        return result;
    }
}
