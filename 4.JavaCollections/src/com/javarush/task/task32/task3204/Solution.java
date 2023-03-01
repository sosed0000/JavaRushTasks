package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Генератор паролей
*/

public class Solution {


    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        while (true) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                s.append(chars.charAt((int) (Math.random() * 62)));//62
            }
            Pattern p1 = Pattern.compile("[0-9]");
            Pattern p2 = Pattern.compile("[a-z]");
            Pattern p3 = Pattern.compile("[A-Z]");
            Matcher m1 = p1.matcher(s.toString());
            Matcher m2 = p2.matcher(s.toString());
            Matcher m3 = p3.matcher(s.toString());
            if (m1.find() && m2.find() && m3.find()) {
                try {
                    byteArrayOutputStream.write(s.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        return byteArrayOutputStream;
    }
}
