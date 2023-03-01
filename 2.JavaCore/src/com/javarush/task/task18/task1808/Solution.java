package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        String file01PassName = null;
        String file02PassName = null;
        String file03PassName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file01PassName = reader.readLine();
            file02PassName = reader.readLine();
            file03PassName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream(file01PassName);
        FileOutputStream outputStream02 = new FileOutputStream(file02PassName);
        FileOutputStream outputStream03 = new FileOutputStream(file03PassName)
        ) {
            if (inputStream.available() > 0) {
                byte[] buffer = new byte[inputStream.available() - inputStream.available()/2];
                inputStream.read(buffer);
                outputStream02.write(buffer);
                buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                outputStream03.write(buffer);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
