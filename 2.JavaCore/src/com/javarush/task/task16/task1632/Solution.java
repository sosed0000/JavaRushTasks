package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {


        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }


    public static void main(String[] args) throws InterruptedException {
      //  threads.forEach(thread -> thread.start());
        threads.get(3).start();
        Thread.sleep(500);
        ((Thread4) threads.get(3)).showWarning();
    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) ;
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }


    }

    public static class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static class Thread4 extends Thread implements Message {
        @Override
        public void run() {
            while (true)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().isAlive());
                    super.interrupt();
                }
            }

        }

        @Override
        public void showWarning() {
                this.interrupt();

        }
    }

    public static class Thread5 extends Thread {
        @Override
        public void run() {
            sum();
        }

        private void sum() {
            double result = 0;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
               // String in = reader.readLine();
                Scanner scanner; // = new Scanner(in);
                String in = "";
                 do {
                     scanner = new Scanner(reader.readLine());
                    if (scanner.hasNextDouble()) {result += scanner.nextDouble();}
                    else in = scanner.nextLine();
                }
                while (!in.equals("N"));
                System.out.println(result);
                } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println();
        }
    }

}