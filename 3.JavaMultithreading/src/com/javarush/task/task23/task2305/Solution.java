package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution01 = new Solution();
        solution01.innerClasses[0] = solution01.new InnerClass();
        solution01.innerClasses[1] = solution01.new InnerClass();
        Solution solution02 = new Solution();
        solution02.innerClasses[0] = solution02.new InnerClass();
        solution02.innerClasses[1] = solution02.new InnerClass();
        return new Solution[] {solution01, solution02};
    }

    public static void main(String[] args) {

    }
}
