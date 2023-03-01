package com.javarush.task.task19.task1913;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Выводим только цифры
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream myOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(myOutputStream);
        System.setOut(myPrintStream);
        testString.printSomething();
        String result = myOutputStream.toString().replaceAll("\\D", "");
        System.setOut(systemOut);
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
