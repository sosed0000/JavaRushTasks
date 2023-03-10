package com.javarush.task.task20.task2016;

import java.io.Serializable;

/* 
Минимум изменений
*/

public class Solution {
    public class A {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class B extends A implements Serializable {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public class C extends B implements Serializable{
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) {

    }
}
