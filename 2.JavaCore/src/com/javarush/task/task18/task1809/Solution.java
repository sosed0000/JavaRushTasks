package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        String file01PassName = null;
        String file02PassName = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file01PassName = reader.readLine();
            file02PassName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream(file01PassName);
             FileOutputStream outputStream = new FileOutputStream(file02PassName);
        ) {
            int[] input = new int[inputStream.available()];
            int count = 0;
            while (inputStream.available() > 0) {
                input[count++] = inputStream.read();
            }
            for (int i = input.length - 1; i >= 0; i--) {
                outputStream.write(input[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
