package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    public static void main(String[] args) {
        System.out.println(labels);
    }

    static {
        labels.put(5.2, "asdfg");
        labels.put(2.0, "asdadsffg");
        labels.put(4.8, "afassdadsffg");
        labels.put(-56.2, "afasdffassdadsffg");
        labels.put(-6.2, "afafa33fg");

    }
}
