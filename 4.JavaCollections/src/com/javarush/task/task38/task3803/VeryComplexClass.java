package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = "wewet";
        int i = (int) o;
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        s.trim();
    }

    public static void main(String[] args) {
    }
}
