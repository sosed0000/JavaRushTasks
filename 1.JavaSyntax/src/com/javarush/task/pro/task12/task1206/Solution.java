package com.javarush.task.pro.task12.task1206;

/* 
Анализ строк
*/

public class Solution {
    public static void main(String[] args) {
        String string = "Думаю, это будет новой фичей." +
                "Только не говорите никому, что она 22возникла 1случайно.";

        System.out.println("Количество цифр в строке : " + countDigits(string));
        System.out.println("Количество букв в строке : " + countLetters(string));
        System.out.println("Количество пробелов в строке : " + countSpaces(string));
    }

    public static int countDigits(String string) {
        //напишите тут ваш код
        int count = 0;
        for (Character ch:
             string.toCharArray()) {if (Character.isDigit(ch)) count++;}
        return count;
    }

    public static int countLetters(String string) {
        //напишите тут ваш код
        int count = 0;
        for (Character ch:
                string.toCharArray()) {if (Character.isLetter(ch)) count++;}
        return count;
    }

    public static int countSpaces(String string) {
        //напишите тут ваш код
        int count = 0;
        for (Character ch:
                string.toCharArray()) {if (Character.isSpaceChar(ch)) count++;}
        return count;
    }
}
