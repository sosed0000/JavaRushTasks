package com.javarush.task.pro.task16.task1619;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* 
Простой шаблон
*/

public class Solution {

    static LocalDateTime localDateTime = LocalDateTime.now();

    public static void main(String[] args) {
        //напишите тут ваш код
        DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyyг. Hч.mмин");
        System.out.println(dateTimeFormatterDate.format(localDateTime));
    }
}
