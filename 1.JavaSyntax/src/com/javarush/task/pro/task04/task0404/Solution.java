package com.javarush.task.pro.task04.task0404;

/* 
Заполненный прямоугольник
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int i = 0, j =0;
        while (i < 5) {
            while (j < 10) {
                System.out.print('Q');
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }

    }
}