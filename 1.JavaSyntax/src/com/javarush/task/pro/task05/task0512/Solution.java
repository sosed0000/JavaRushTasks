package com.javarush.task.pro.task05.task0512;

/* 
Создаем мультимассив
*/

import java.util.Arrays;

public class Solution {

    public static int[][][] multiArray = new int[][][]{{{4, 8, 15}, {16}}, {{23, 42}, {}}, {{1}, {2}, {3}, {4, 5}}};

    public static void main(String[] args) {
        //напишите тут ваш код
        System.out.println(Arrays.asList(Arrays.deepToString(multiArray)));
      /*  for (int[][] ar1:
             multiArray) {
            for (int[] ar2:
                 ar1) {
                for (int x:
                     ar2) {
                    System.out.println(x);
                }

            }


        }

       */
    }
}
