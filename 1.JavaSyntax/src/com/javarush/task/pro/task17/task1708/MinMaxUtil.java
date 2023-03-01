package com.javarush.task.pro.task17.task1708;

/* 
Минимальное и максимальное
*/

import java.util.SortedSet;
import java.util.TreeSet;

public class MinMaxUtil {
    //напишите тут ваш код
    public static int min (int a, int b) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        return sortedSet.first();
    }

    public static int min (int a, int b, int c) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        return sortedSet.first();
    }

    public static int min (int a, int b, int c, int d) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        sortedSet.add(d);
        return sortedSet.first();
    }

    public static int min (int a, int b, int c, int d, int e) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        sortedSet.add(d);
        sortedSet.add(e);
        return sortedSet.first();
    }

    public static int max (int a, int b) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        return sortedSet.last();
    }

    public static int max (int a, int b, int c) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        return sortedSet.last();
    }

    public static int max (int a, int b, int c, int d) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        sortedSet.add(d);
        return sortedSet.last();
    }

    public static int max (int a, int b, int c, int d, int e) {
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();
        sortedSet.add(a);
        sortedSet.add(b);
        sortedSet.add(c);
        sortedSet.add(d);
        sortedSet.add(e);
        return sortedSet.last();
    }

}
