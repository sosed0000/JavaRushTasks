package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String AD = "JavaRush - курсы Java онлайн";
        PrintStream originalStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String[] strings = outputStream.toString().split("\n");

        System.setOut(originalStream);
        for (int i = 0; i < strings.length; i++){
            System.out.println(strings[i]);
            if (++i == strings.length) {break;}
            System.out.println(strings[i]);
            System.out.println(AD);
        }


    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }


}
