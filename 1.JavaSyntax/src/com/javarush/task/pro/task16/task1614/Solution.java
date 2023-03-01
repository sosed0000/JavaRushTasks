package com.javarush.task.pro.task16.task1614;

import java.time.DateTimeException;
import java.time.Instant;

/* 
Конец времен
*/

public class Solution {

    public static void main(String[] args) {
/*
        Long l = Long.valueOf(31556889865001025L);
        while (true) {
            l--;

            try {

                Instant.ofEpochSecond(l);
                System.out.println(l);
                return;

            }
            catch (DateTimeException e) {
                System.out.println(l);
                continue;
            }
        }
*/     System.out.println(getMaxFromMilliseconds());
        System.out.println(getMaxFromSeconds());
        System.out.println(getMaxFromSecondsAndNanos());


    }

    static Instant getMaxFromMilliseconds() {
        //напишите тут ваш код
        return  Instant.ofEpochMilli(Long.MAX_VALUE);
    }

    static Instant getMaxFromSeconds() {
        //напишите тут ваш код

        return  Instant.ofEpochSecond(31556889864403199L);
    }

    static Instant getMaxFromSecondsAndNanos() {
        //напишите тут ваш код

        return  Instant.ofEpochSecond(31556889864403199L, 1000000000-1);
    }
}
