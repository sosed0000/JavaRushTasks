package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream myOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(myOutputStream);
        System.setOut(myPrintStream);
        testString.printSomething();
        String[] data = myOutputStream.toString().split(" ");
        int x = Integer.parseInt(data[0]);
        int y = Integer.parseInt(data[2]);
        String operand = data[1];
        int res = 0;
        if (operand.equals("+")) {
            res = x + y;
        } else if (operand.equals("-")) {
            res = x - y;
        } else if (operand.equals("*")) {
            res = x * y;
        }
        String result = String.format("%d %s %d = %d", x, operand, y, res);
        System.setOut(systemOut);
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

