package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) {
        String file1Name = "";
        String file2Name = "";
        byte[] toFile = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1Name = reader.readLine();
            file2Name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream reader1 = new FileInputStream(file1Name);
             FileInputStream reader2 = new FileInputStream(file2Name)
        ) {
            toFile = new byte[reader2.available() + reader1.available()];
            int i = 0;
            while (reader2.available() > 0) {
              toFile[i++] = (byte) reader2.read();
            }
            while (reader1.available() > 0) {
                toFile[i++] = (byte) reader1.read();
            }


            /*
            byte[] file2 = reader2.readAllBytes();
            writer.write(file2);
            byte[] file3 = reader3.readAllBytes();
            writer.write(file3);

             */

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream writer = new FileOutputStream(file1Name)) {
            writer.write(toFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
