package com.javarush.task.pro.task03.task0305;

import java.util.Scanner;

/* 
Три числа
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        int a, b, c;

        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        if (a == b & b == c) System.out.println(a + " " + b +  " " + c);
        else if (a == b) System.out.println(a + " " + b);
        else if (b == c) System.out.println(b + " " + c);
        else if (a == c) System.out.println(a + " " + c);

    }
}
