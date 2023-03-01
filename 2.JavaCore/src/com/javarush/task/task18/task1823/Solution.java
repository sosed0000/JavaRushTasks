package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static volatile Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String file;
            while (!(file = reader.readLine()).equals("exit")) {
                new ReadThread(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

     //   resultMap.forEach((s, integer) -> System.out.println(s + " " + (char) (int) integer));
    }

    public static class ReadThread extends Thread {
        private String file;

        public ReadThread(String fileName) {
            //implement constructor body
            file = fileName;
            start();
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            HashMap<Integer, Integer> bytes = new HashMap();
            try (FileReader reader = new FileReader(file)) {
                while (reader.ready()) {
                    int currentByte = reader.read();
                    if (bytes.containsKey(currentByte)) {
                        bytes.put(currentByte, bytes.get(currentByte) + 1);

                    } else
                        bytes.put(currentByte, 1);
                }

                int max = 0;
                for (Integer i :
                        bytes.values()) {
                    max = Math.max(max, i);

                }
                Set<Map.Entry<Integer, Integer>> entrySet = bytes.entrySet();
                int finalMax = max;

                entrySet.removeIf(entry -> entry.getValue() < finalMax);


                synchronized (resultMap) {
                    entrySet.forEach(entry -> resultMap.put(file, entry.getKey()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
