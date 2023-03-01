package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int x, y;
       // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        x = scanner.nextInt();
        y = scanner.nextInt();

        for (int gcd = Math.min(x, y); gcd > 0; gcd--) {
            if (x % gcd == 0 && y % gcd == 0) {
                System.out.println(gcd);
                break;
            }
        }

        scanner.close();
    }
}
