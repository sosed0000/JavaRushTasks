package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String file = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintStream systemOut = System.out;
        ByteArrayOutputStream myOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(myOutputStream);
        System.setOut(myPrintStream);
        testString.printSomething();
        String result = myOutputStream.toString();

        try (FileOutputStream writer = new FileOutputStream(file)) {
           writer.write(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.setOut(systemOut);
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

