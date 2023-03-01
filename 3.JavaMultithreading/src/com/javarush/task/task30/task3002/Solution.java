package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        int radix = 10;
        if (s.charAt(0) == '0'){
            if ((s.charAt(1) + "").equalsIgnoreCase("b")) {
                radix = 2;
                s = s.substring(2);
            }
            else if ((s.charAt(1) + "").equalsIgnoreCase("x")) {
                radix = 16;
                s = s.substring(2);
            }
            else {
                radix = 8;
                s = s.substring(1);
            }
        }

        return String.valueOf(Integer.parseInt(s, radix));
    }
}
