package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static CanFly result;

    public static void reset() {
        //add your code here - добавьте код тут
        Scanner scanner = new Scanner(System.in);
        String flyingObject = scanner.nextLine();
        if (flyingObject.equals("helicopter")) {result = new Helicopter();}
        else if (flyingObject.equals("plane")) {
            int passengers = Integer.parseInt(scanner.nextLine());
            result = new Plane(passengers);
        }
        scanner.close();
    }
}
