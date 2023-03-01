package com.javarush.task.task18.task1818;

import java.io.*;
import java.util.Scanner;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) {
        String file1Name = "";
        String file2Name = "";
        String file3Name = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1Name = reader.readLine();
            file2Name = reader.readLine();
            file3Name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream writer = new FileOutputStream(file1Name);
             FileInputStream reader2 = new FileInputStream(file2Name);
             FileInputStream reader3 = new FileInputStream(file3Name)
        ) {
            while (reader2.available() > 0) {
                writer.write(reader2.read());
            }
            while (reader3.available() > 0) {
                writer.write(reader3.read());
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

    }
}
