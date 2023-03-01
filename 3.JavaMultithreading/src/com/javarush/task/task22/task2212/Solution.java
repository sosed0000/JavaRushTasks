package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
//        String patternFalse01 = ".*[0-9]";
//        if (!telNumber.matches(patternFalse01)) return false;
//
//
//
//
//        String patternTrue01 = "\\+[0-9]{12}";
//        if (telNumber.matches(patternTrue01)) return true;
//        String patternTrue02 = "\\+[0-9]{2}\\(?[0-9]{3}\\)?[0-9]{7}";
//        if (telNumber.matches(patternTrue02)) return true;
//        String patternTrue03 = "[0-9]{10}";
//        if (telNumber.matches(patternTrue03)) return true;
//        String patternTrue04 = "\\([0-9]{3}\\)[0-9]{7}";
//        if (telNumber.matches(patternTrue04)) return true;
//        String patternTrue05 = "[0-9]\\([0-9]{3}\\)[0-9]{6}";
//        if (telNumber.matches(patternTrue05)) return true;
//        String patternTrue06 = "\\+\\d*\\([0-9]{3}\\)\\d*";
//        if (telNumber.matches(patternTrue06)) {
//            int countDigits = 0;
//            for (char ch : telNumber.toCharArray()) {
//                if (Character.isDigit(ch)) countDigits++;
//            }
//            if (countDigits == 12) return true;
//        }
//        String patternTrue07 = "\\d*\\([0-9]{3}\\)\\d*";
//        if (telNumber.matches(patternTrue07)) {
//            int countDigits = 0;
//            for (char ch : telNumber.toCharArray()) {
//                if (Character.isDigit(ch)) countDigits++;
//            }
//            if (countDigits == 10) return true;
//        }
        return (telNumber.matches("^\\+(\\d[()]?){12}$") || telNumber.matches("^([()]?\\d){10}$"))
                && telNumber.matches("^(\\+)?(\\d+)?(\\(\\d{3}\\))?\\d+$");
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("0501(234)167"));
//        System.out.println(checkTelNumber("+38(1050)234567"));
//        System.out.println(checkTelNumber("(050)1234567"));
//        System.out.println(checkTelNumber("0(501)234567"));
//        System.out.println(checkTelNumber("38)050(1234567"));
//        System.out.println(checkTelNumber("38(050)123-45-67"));
//        System.out.println(checkTelNumber("050ххх4567"));
//        System.out.println(checkTelNumber("050123456"));
//        System.out.println(checkTelNumber("(0)501234567"));
    }
}
