package com.javarush.task.pro.task09.task0908;

import java.util.regex.Pattern;

/* 
Двоично-шестнадцатеричный конвертер
*/

public class Solution {

    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        //напишите тут ваш код
        if (binaryNumber == null || binaryNumber.isEmpty() || !binaryNumber.matches("[0-1]+")) return "";
        int count = (binaryNumber.length() % 4);
        while (count > 0 & count < 4) {
            binaryNumber = "0" + binaryNumber;
            count++;
        }
        count = 0;
        String hexNumber = "";
        while (count < binaryNumber.length()) {
            hexNumber = hexNumber + binaryHex(binaryNumber.substring(count, count + 4));
            count += 4;
        }
        return hexNumber;
    }

    public static String toBinary(String hexNumber) {
        //напишите тут ваш код
        if (hexNumber == null || hexNumber.isEmpty() || !hexNumber.matches("[0-9,a-f]+")) return "";
        String binaryNumber = "";
        int count = 0;
        while (count < hexNumber.length()) {
            binaryNumber = binaryNumber + heXbinary(hexNumber.charAt(count) + "");
            count++;
        }
        return binaryNumber;
    }

    public static String binaryHex(String binary) {
        switch (binary) {
            case "0000":
                return "0";
            case "0001":
                return "1";
            case "0010":
                return "2";
            case "0011":
                return "3";
            case "0100":
                return "4";
            case "0101":
                return "5";
            case "0110":
                return "6";
            case "0111":
                return "7";
            case "1000":
                return "8";
            case "1001":
                return "9";
            case "1010":
                return "a";
            case "1011":
                return "b";
            case "1100":
                return "c";
            case "1101":
                return "d";
            case "1110":
                return "e";
            case "1111":
                return "f";
        }
        return null;
    }

    public static String heXbinary(String hex) {
        switch (hex) {
            case "0":
                return "0000";
            case "1":
                return "0001";
            case "2":
                return "0010";
            case "3":
                return "0011";
            case "4":
                return "0100";
            case "5":
                return "0101";
            case "6":
                return "0110";
            case "7":
                return "0111";
            case "8":
                return "1000";
            case "9":
                return "1001";
            case "a":
                return "1010";
            case "b":
                return "1011";
            case "c":
                return "1100";
            case "d":
                return "1101";
            case "e":
                return "1110";
            case "f":
                return "1111";
        }
        return null;
    }
}
