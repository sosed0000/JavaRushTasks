package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

/* 
Ридер обертка 2
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream myOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(myOutputStream);
        System.setOut(myPrintStream);
        testString.printSomething();
        String result = myOutputStream.toString().replaceAll("te", "??");
        System.setOut(systemOut);
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
