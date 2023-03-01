package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            String[] words = string.split("\t");
            if (words.length < 3) {
                throw new TooShortStringException();
            }
            return words[1];
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("eee\tJavaRush - лучший сервис \tобучения Java\taf."));
    }
}
