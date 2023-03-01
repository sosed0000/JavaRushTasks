package com.javarush.task.pro.task13.task1319;

/* 
Месяцы в сезоне
*/

public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;
    public static Month[] winterMonths = {DECEMBER, JANUARY, FEBRUARY};
    public static Month[] springMonths = {MARCH, APRIL, MAY};
    public static Month[] summerMonths = {JUNE, JULY, AUGUST};
    public static Month[] autumnMonths = {SEPTEMBER, OCTOBER, NOVEMBER};

    //напишите тут ваш код
    public static Month[] getWinterMonths() {return winterMonths;}
    public static Month[] getSpringMonths() {return springMonths;}
    public static Month[] getSummerMonths() {return summerMonths;}
    public static Month[] getAutumnMonths() {return autumnMonths;}

}
