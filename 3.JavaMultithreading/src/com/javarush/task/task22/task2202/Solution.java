package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/



public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("Амиго изья!"));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();
        String[] words = string.split(" ");
        try {
            return new String(words[1] + " " + words[2] + " " + words[3]  + " " + words[4]);
        } catch (Exception e) {
            throw new TooShortStringException();
        }

    }
    public static class TooShortStringException extends RuntimeException {
    }
}
