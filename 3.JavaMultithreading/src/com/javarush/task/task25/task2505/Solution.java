package com.javarush.task.task25.task2505;

/* 
Без дураков
*/

public class Solution {

    public static void main(String[] args) {

        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;


        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//            setDaemon(true);
        }


        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

            MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                String message = String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage());
                System.out.printf(message);
            }
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");

        }
    }

}

