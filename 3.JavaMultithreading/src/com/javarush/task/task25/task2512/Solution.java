package com.javarush.task.task25.task2512;

/*
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        printStackTrace(e);


    }

    private void printStackTrace(Throwable e) {
        if (e.getCause() == null) {
            System.out.println(e);
            return;
        }
        printStackTrace(e.getCause());
        System.out.println(e);

    }

    public static void main(String[] args) {
//        try {
//            throw new RuntimeException(new NullPointerException("sdfgh"));
//        } catch (Exception e) {
//            printStackTrace(e);
//        }
    }
}
